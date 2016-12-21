package com.bsuir.shoken.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDealRepository extends JpaRepository<Deal, Long>, DealRepository {
}
