package com.bsuir.shoken.iam;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
