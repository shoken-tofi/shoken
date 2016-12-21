package com.bsuir.shoken.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface DealRepository extends CrudRepository<Deal, Long> {

    Deal findOneByBidId(final Long bidId);
}
