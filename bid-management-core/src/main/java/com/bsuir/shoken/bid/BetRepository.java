package com.bsuir.shoken.bid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@NoRepositoryBean
interface BetRepository extends PagingAndSortingRepository<Bet, Long> {

    Page<Bet> findByBidId(final Long bidId, final Pageable pageable);

    Optional<Bet> findFirstByBidIdAndInvestorIdOrderByValueDesc(final Long bidId, final Long investorId);

    Optional<Bet> findFirstByBidIdOrderByValueDesc(final Long bidId);
}
