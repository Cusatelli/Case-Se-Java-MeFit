package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Program with JpaRepository.
 */
@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> { }
