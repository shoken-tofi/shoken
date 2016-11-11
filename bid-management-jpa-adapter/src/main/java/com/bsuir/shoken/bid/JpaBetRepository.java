package com.bsuir.shoken.bid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBetRepository extends JpaRepository<Bet, Long>, BetRepository {
}
