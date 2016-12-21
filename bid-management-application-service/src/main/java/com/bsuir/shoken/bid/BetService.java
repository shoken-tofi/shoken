package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import com.bsuir.shoken.iam.SecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
public class BetService {

    private final BetRepository betRepository;

    private final BidRepository bidRepository;

    private final SecurityContextService securityContextService;

    private final SellerService sellerService;

    @Transactional(readOnly = true)
    Page<Bet> findByBidId(final Long bidId, final Pageable pageable) {
        return betRepository.findByBidId(bidId, pageable);
    }

    @Transactional(readOnly = true)
    Optional<Bet> findMaxByBidId(final Long bidId) {
        return betRepository.findFirstByBidIdOrderByValueDesc(bidId);
    }

    public Bet create(final Bet bet) throws NoSuchEntityException, ValidationException {

        final Long bidId = bet.getBidId();
        final Optional<Bid> bid = bidRepository.findOneById(bidId);
        if (!bid.isPresent()) {
            throw new NoSuchEntityException("Bid with such id = " + bidId + " doesn't exists.");
        }

        final String username = securityContextService.getUsername();
        final Seller seller = sellerService.findByName(username);
        if (bid.get().getSellerId().equals(seller.getId())) {
            throw new ValidationException("Seller can't create bet.");
        }

        final BigDecimal currentMaxValue = betRepository.findFirstByBidIdOrderByValueDesc(bidId)
                .orElseGet(Bet::new)
                .getValue();

        final BigDecimal value = bet.getValue();
        if (currentMaxValue != null && currentMaxValue.compareTo(value) >= 0) {
            throw new ValidationException("Bet value = " + value + " is less or equal to current max value.");
        }

        return betRepository.save(new Bet(bet.getInvestorId(), bidId, value));
    }
}
