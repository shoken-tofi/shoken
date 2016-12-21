package com.bsuir.shoken.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBillRepository extends JpaRepository<Bill, Long>, BillRepository {
}
