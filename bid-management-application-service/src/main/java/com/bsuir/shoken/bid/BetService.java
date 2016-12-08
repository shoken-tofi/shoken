package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
public class BetService {

    private final BetRepository betRepository;

    @Transactional(readOnly = true)
    List<Bet> findByBidId(final Long bidId) {
        return betRepository.findByBidIdOrderByValueDesc(bidId);
    }

    @Transactional(readOnly = true)
    List<Bet> findByInvestorId(final Long investorId) {
        return betRepository.findByInvestorIdOrderByValueDesc(investorId);
    }

    @Transactional(readOnly = true)
    List<Bet> findByBidIdAndInvestorId(final Long bidId, final Long investorId) {
        return betRepository.findByBidIdAndInvestorIdOrderByValueDesc(bidId, investorId);
    }

    @Transactional(readOnly = true)
    Optional<Bet> findFirstByBidId(final Long bidId) {
        return betRepository.findFirstByBidIdOrderByValueDesc(bidId);
    }

    public Bet create(final Bet bet) {
        return betRepository.save(bet);
    }
}
