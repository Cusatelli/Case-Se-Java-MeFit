package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Exercise with JpaRepository.
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> { }
