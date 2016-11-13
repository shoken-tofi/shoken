package com.bsuir.shoken.bid;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
interface BidRepository extends PagingAndSortingRepository<Bid, Long> {
}
