package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in User with JpaRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> { }
