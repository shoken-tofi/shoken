package com.bsuir.shoken.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVendeeRepository extends JpaRepository<Vendee, Long>, VendeeRepository {
}
