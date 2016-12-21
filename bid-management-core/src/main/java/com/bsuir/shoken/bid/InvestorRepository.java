package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
interface InvestorRepository extends CrudRepository<Investor, Long> {

    Optional<Investor> findOneByName(final String name);
}
