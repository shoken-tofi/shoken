package com.bsuir.shoken.bid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSellerRepository extends JpaRepository<Seller, Long>, SellerRepository {
}
