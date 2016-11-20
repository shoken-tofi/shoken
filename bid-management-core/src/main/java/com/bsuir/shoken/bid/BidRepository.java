package com.bsuir.shoken.bid;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
interface BidRepository extends PagingAndSortingRepository<Bid, Long>, QueryByExampleExecutor<Bid> {
}
