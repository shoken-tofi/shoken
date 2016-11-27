package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
interface BetRepository extends CrudRepository<Bet, Long> {

    List<Bet> findByBidIdOrderByValueDesc(Long bidId);

    List<Bet> findByInvestorIdOrderByValueDesc(Long investorId);

    List<Bet> findByBidIdAndInvestorIdOrderByValueDesc(Long bidId, Long investorId);

    Optional<Bet> findFirstByBidIdOrderByValueDesc(Long bidId);
}
