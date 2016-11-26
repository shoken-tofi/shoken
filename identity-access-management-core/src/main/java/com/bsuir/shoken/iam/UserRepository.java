package com.bsuir.shoken.iam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@NoRepositoryBean
interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findAllOrderByRegistrationDateDesc(final Pageable pageable);

    Optional<User> findOneByLogin(final String login);

    Optional<User> findOneByEmail(final String email);
}
