package com.bsuir.shoken.bid;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@NoRepositoryBean
interface BidRepository extends PagingAndSortingRepository<Bid, Long>, QueryDslPredicateExecutor<Bid> {

    Optional<Bid> findOneById(final Long id);
}
