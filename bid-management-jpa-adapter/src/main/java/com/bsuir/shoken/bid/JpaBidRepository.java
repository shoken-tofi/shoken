package com.bsuir.shoken.bid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBidRepository extends JpaRepository<Bid, Long>, BidRepository {
}
