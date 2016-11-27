package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface InvestorRepository extends CrudRepository<Investor, Long> {
}
