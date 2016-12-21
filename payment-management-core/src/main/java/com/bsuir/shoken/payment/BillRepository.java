package com.bsuir.shoken.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface BillRepository extends CrudRepository<Bill, Long> {
}
