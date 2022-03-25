package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Set with JpaRepository.
 */
@Repository
public interface SetRepository extends JpaRepository<Set, Long> { }
