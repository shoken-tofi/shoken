package com.bsuir.shoken.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVendorRepository extends JpaRepository<Vendor, Long>, VendorRepository {
}
