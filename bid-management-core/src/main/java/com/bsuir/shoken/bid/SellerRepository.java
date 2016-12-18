package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
interface SellerRepository extends CrudRepository<Seller, Long> {

    Optional<Seller> findOneByName(final String name);
}
