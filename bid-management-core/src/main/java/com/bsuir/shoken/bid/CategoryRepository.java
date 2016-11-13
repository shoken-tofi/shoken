package com.bsuir.shoken.bid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();
}
