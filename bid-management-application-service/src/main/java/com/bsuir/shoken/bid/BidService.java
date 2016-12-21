package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import com.bsuir.shoken.iam.SecurityContextService;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.bsuir.shoken.bid.QBid.bid;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
public class BidService {

    private final BidRepository bidRepository;

    private final TaskScheduler taskScheduler;

    private final BetRepository betRepository;

    private final SecurityContextService securityContextService;

    private final SellerRepository sellerRepository;

    private final InvestorRepository investorRepository;

    private final ApplicationEventPublisher publisher;

    @Transactional(readOnly = true)
    Page<Bid> findAll(final SearchCriteria criteria, final Pageable pageable) {

        final BooleanBuilder builder = buildPredicate(criteria);

        return bidRepository.findAll(builder.getValue(), pageable);
    }

    @Transactional(readOnly = true)
    Page<Bid> findAllForSeller(final Long sellerId, final SearchCriteria criteria, final Pageable pageable) {

        final BooleanBuilder builder = buildPredicate(criteria);

        builder.and(bid.sellerId.eq(sellerId));

        return bidRepository.findAll(builder.getValue(), pageable);
    }

    @Transactional(readOnly = true)
    Page<Bid> findAllForInvestor(final Long investorId, final SearchCriteria criteria, final Pageable pageable) {

        final BooleanBuilder builder = buildPredicate(criteria);

        builder.and(bid.bets.any().investorId.eq(investorId));
        if (Bid.Status.ACTIVE != criteria.getStatus()) {
            builder.and(bid.bets.any().value.eq(bid.bets.any().value.max()));
        }

        return bidRepository.findAll(builder.getValue(), pageable);
    }

    private BooleanBuilder buildPredicate(final SearchCriteria criteria) {

        final BooleanBuilder builder = new BooleanBuilder();

        final String query = criteria.getQuery();
        if (!StringUtils.isEmpty(query)) {
            builder.and(bid.title.containsIgnoreCase(query).or(bid.description.containsIgnoreCase(query)));
        }

        final BigDecimal minBetValue = criteria.getMinBetPrice();
        if (minBetValue != null) {
            builder.and(bid.bets.any().value.goe(minBetValue).or(bid.startPrice.goe(minBetValue)));
        }
        final BigDecimal maxBetValue = criteria.getMaxBetPrice();
        if (maxBetValue != null) {
            builder.and(bid.bets.any().value.loe(maxBetValue).or(bid.startPrice.loe(maxBetValue)));
        }

        final List<Bid.Type> types = criteria.getBidTypes();
        if (types != null && !types.isEmpty()) {
            builder.and(bid.type.in(types));
        }

        final LocalDateTime minStartDate = criteria.getMinStartDate();
        if (minStartDate != null) {
            builder.and(bid.creationDate.loe(minStartDate));
        }
        final LocalDateTime maxStartDate = criteria.getMaxStartDate();
        if (maxStartDate != null) {
            builder.and(bid.creationDate.goe(maxStartDate));
        }

        final LocalDateTime minExpirationDate = criteria.getMinExpirationDate();
        if (minExpirationDate != null) {
            builder.and(bid.expirationDate.goe(minExpirationDate));
        }
        final LocalDateTime maxExpirationDate = criteria.getMaxExpirationDate();
        if (maxExpirationDate != null) {
            builder.and(bid.expirationDate.loe(maxExpirationDate));
        }

        builder.and(bid.status.eq(criteria.getStatus()));

        return builder;
    }

    @Transactional(readOnly = true)
    BidVO findOne(final Long id) throws NoSuchEntityException, ValidationException {

        final Bid bid = bidRepository.findOneById(id).orElseThrow(() ->
                new NoSuchEntityException("Bid with such id = " + id + " doesn't exists."));

        if (!securityContextService.isAuthenticated()) {
            if (Bid.Status.ACTIVE != bid.getStatus()) {
                throw new ValidationException("Access to bid with such id = " + id + " denied.");
            }

            return new BidVO(bid, false, false);
        }

        if (securityContextService.isAdmin()) {
            if (Bid.Status.ACTIVE != bid.getStatus()) {
                return new BidVO(bid, false, false);
            }

            return new BidVO(bid, true, false);
        }

        final String username = securityContextService.getUsername();

        final Seller seller = sellerRepository.findOneByName(username)
                .orElseThrow(() -> new NoSuchEntityException("Seller with such name = " + username + " doesn't exists."));

        boolean canDelete = false;
        boolean canBet = false;

        switch (bid.getStatus()) {
            case ACTIVE:

                if (!bid.getSellerId().equals(seller.getId())) {
                    canBet = true;
                } else {
                    canDelete = true;
                }

                break;
            case DELETED:

                if (!bid.getSellerId().equals(seller.getId())) {
                    throw new ValidationException("Access to bid with such id = " + id + " denied.");
                }

                break;
            case CANCELED:
            case IN_PAYMENT:
            case FINISHED:

                if (!bid.getSellerId().equals(seller.getId())) {
                    final Investor investor = investorRepository.findOneByName(username)
                            .orElseThrow(() ->
                                    new NoSuchEntityException("Investor with such name = " + username + " doesn't exists."));
                    final Bet maxBet = betRepository.findFirstByBidIdOrderByValueDesc(id).orElseGet(Bet::new);

                    if (!investor.getId().equals(maxBet.getInvestorId())) {
                        throw new ValidationException("Access to bid with such id = " + id + " denied.");
                    }
                }

                break;
        }

        return new BidVO(bid, canDelete, canBet);
    }

    public Bid create(final Bid bid) {

        final Bid bidFromDatabase = bidRepository.save(bid);

        final Date expirationDate = Date.from(bid.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant());
        taskScheduler.schedule(() -> expire(bidFromDatabase), expirationDate);

        return bidFromDatabase;
    }

    @Async
    private void expire(final Bid bid) {

        final Bid.Status status;

        final Optional<Bet> maxBet = betRepository.findFirstByBidIdOrderByValueDesc(bid.getId());
        if (!maxBet.isPresent()) {
            status = Bid.Status.DELETED;
        } else {
            status = Bid.Status.IN_PAYMENT;

            publisher.publishEvent(new BidFinishedEventVO(bid, maxBet.get()));
        }

        bid.setStatus(status);
        bidRepository.save(bid);
    }

    void delete(final Long id) throws NoSuchEntityException, ValidationException {

        final Bid bid = bidRepository.findOneById(id)
                .orElseThrow(() -> new NoSuchEntityException("Bid with such id = " + id + " doesn't exists."));
        final Bid.Status status = bid.getStatus();

        final Bid.Status deleted = Bid.Status.DELETED;

        if (status != Bid.Status.ACTIVE) {
            if (status == deleted) {
                throw new ValidationException("Bid with id = " + id + " already deleted.");
            }

            throw new ValidationException("Bid with id = " + id + " can't be deleted.");
        }

        if (!securityContextService.isAdmin()) {
            Optional<Bet> maxBet = betRepository.findFirstByBidIdOrderByValueDesc(id);
            if (maxBet.isPresent()) {
                throw new ValidationException("Bid with id = " + id + " contains active bets.");
            }
        }

        bid.setStatus(deleted);
        bidRepository.save(bid);
    }
}
