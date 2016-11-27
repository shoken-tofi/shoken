package com.bsuir.shoken.bid;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

@NoRepositoryBean
interface BidRepository extends PagingAndSortingRepository<Bid, Long>, QueryByExampleExecutor<Bid> {

    Optional<Bid> findOneById(final Long id);
}
