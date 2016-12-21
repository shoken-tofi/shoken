package com.bsuir.shoken.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
interface VendeeRepository extends CrudRepository<Vendee, Long> {

    Optional<Vendee> findOneByName(final String name);
}
