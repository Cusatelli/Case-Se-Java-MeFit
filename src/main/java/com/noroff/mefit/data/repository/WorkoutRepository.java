package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Workout with JpaRepository.
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> { }
