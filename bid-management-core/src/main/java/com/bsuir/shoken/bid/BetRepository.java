package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
interface BetRepository extends CrudRepository<Bet, Long> {

    List<Bet> findByBidId(Long bidId);

    List<Bet> findByInvestorId(Long investorId);

    List<Bet> findByBidIdAndInvestorId(Long bidId, Long investorId);
}
