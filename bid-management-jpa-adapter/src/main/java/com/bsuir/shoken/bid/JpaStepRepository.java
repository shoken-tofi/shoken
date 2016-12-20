package com.bsuir.shoken.bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface JpaStepRepository extends JpaRepository<Step, Long>, StepRepository {

    @Override
    @Query("select s from Step s where s.low <= :price and s.high >= :price")
    Optional<Step> findByPredicate(@Param("price") final BigDecimal price);
}
