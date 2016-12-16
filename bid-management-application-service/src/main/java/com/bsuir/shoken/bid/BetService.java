package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
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

    @Transactional(readOnly = true)
    Page<Bet> findByBidId(final Long bidId, final Pageable pageable) {
        return betRepository.findByBidIdOrderByValueDesc(bidId, pageable);
    }

    @Transactional(readOnly = true)
    Optional<Bet> findMaxByBidId(final Long bidId) {
        return betRepository.findFirstByBidIdOrderByValueDesc(bidId);
    }

    public Bet create(final Bet bet) throws NoSuchEntityException, ValidationException {

        final Long bidId = bet.getBidId();
        if (!bidRepository.exists(bidId)) {
            throw new NoSuchEntityException("Bid with such id = " + bidId + " doesn't exists.");
        }

        final BigDecimal currentMaxValue = betRepository.findFirstByBidIdOrderByValueDesc(bidId)
                .orElseGet(Bet::new)
                .getValue();

        final BigDecimal value = bet.getValue();
        if (currentMaxValue.compareTo(value) >= 0) {
            throw new ValidationException("Bet value = " + value + " is less or equals to current max value.");
        }

        return betRepository.save(new Bet(bet.getInvestorId(), bidId, value));
    }
}
