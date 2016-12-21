package com.bsuir.shoken.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
interface VendorRepository extends CrudRepository<Vendor, Long> {

    Optional<Vendor> findOneByName(final String name);
}
