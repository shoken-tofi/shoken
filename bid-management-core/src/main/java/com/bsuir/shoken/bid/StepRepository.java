package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.math.BigDecimal;
import java.util.Optional;

@NoRepositoryBean
interface StepRepository extends CrudRepository<Step, Long> {

    Optional<Step> findByPredicate(final BigDecimal price);
}
