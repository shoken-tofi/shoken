package com.bsuir.shoken.bid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInvestorRepository extends JpaRepository<Investor, Long>, InvestorRepository {
}
