package com.bsuir.shoken.bid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
interface BetRepository extends PagingAndSortingRepository<Bet, Long> {

    Page<Bet> findByBidIdOrderByValueDesc(final Long bidId, final Pageable pageable);

    List<Bet> findByInvestorIdOrderByValueDesc(final Long investorId);

    Optional<Bet> findFirstByBidIdAndInvestorIdOrderByValueDesc(final Long bidId, final Long investorId);

    Optional<Bet> findFirstByBidIdOrderByValueDesc(final Long bidId);
}
