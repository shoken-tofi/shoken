package com.bsuir.shoken.iam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}
