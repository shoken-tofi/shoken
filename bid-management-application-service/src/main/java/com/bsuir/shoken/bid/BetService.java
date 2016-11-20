package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class BetService {

    private final BetRepository betRepository;

    @Transactional(readOnly = true)
    List<Bet> findByBidId(Long bidId) {
        return betRepository.findByBidIdOrderByValueDesc(bidId);
    }

    @Transactional(readOnly = true)
    List<Bet> findByInvestorId(Long investorId) {
        return betRepository.findByInvestorIdOrderByValueDesc(investorId);
    }

    @Transactional(readOnly = true)
    List<Bet> findByBidIdAndInvestorId(Long bidId, Long investorId) {
        return betRepository.findByBidIdAndInvestorIdOrderByValueDesc(bidId, investorId);
    }

    @Transactional(readOnly = true)
    Bet findFirstByBidId(Long bidId) {
        return betRepository.findFirstByBidIdOrderByValueDesc(bidId);
    }

    Bet create(Bet bet) {
        return betRepository.save(bet);
    }
}
