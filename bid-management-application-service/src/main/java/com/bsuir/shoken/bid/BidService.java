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

    private final BetRepository betRepository;

    private final TaskScheduler taskScheduler;

    private final SecurityContextService securityContextService;

    private final SellerRepository sellerRepository;

    private final ApplicationEventPublisher publisher;

    @Transactional(readOnly = true)
    Page<Bid> findAll(final SearchCriteria criteria, final Pageable pageable) {

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

        builder.and(bid.status.eq(Bid.Status.ACTIVE));

        return bidRepository.findAll(builder.getValue(), pageable);
    }

    @Transactional(readOnly = true)
    Bid findOne(final Long id) throws NoSuchEntityException, ValidationException {

        final Bid result = bidRepository.findOneById(id).orElseThrow(() ->
                new NoSuchEntityException("Bid with such id = " + id + " doesn't exists."));

        if (Bid.Status.ACTIVE == result.getStatus()) {
            return result;
        }

        if (!securityContextService.isAuthenticated()) {
            throw new ValidationException("Access to bid with such id = " + id + " denied.");
        }

        final String username = securityContextService.getAuthentication();

        switch (result.getStatus()) {
            case DELETED:

                final Seller seller = sellerRepository.findOneByName(username)
                        .orElseThrow(() ->
                                new NoSuchEntityException("Seller with such name = " + username + " doesn't exists."));
                if (!result.getSellerId().equals(seller.getId())) {
                    throw new ValidationException("Access to bid with such id = " + id + " denied.");
                }

                break;
            case CANCELED:
            case IN_PAYMENT:
            case FINISHED:
                break;
        }

        return result;
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

            publisher.publishEvent(new FinishedBidVO(bid, maxBet.get()));
        }

        bid.setStatus(status);
        bidRepository.save(bid);
    }

    void delete(final Long id) throws NoSuchEntityException, ValidationException {

        final Bid bid = bidRepository.findOneById(id)
                .orElseThrow(() -> new NoSuchEntityException("Bid with such id = " + id + " doesn't exists."));

        final Bid.Status deleted = Bid.Status.DELETED;
        if (bid.getStatus() == deleted) {
            throw new ValidationException("Bid with id = " + id + " already deleted.");
        }

        bid.setStatus(deleted);
        bidRepository.save(bid);
    }
}
