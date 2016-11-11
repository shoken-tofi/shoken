package com.bsuir.shoken.bid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Long>, CategoryRepository {
}
