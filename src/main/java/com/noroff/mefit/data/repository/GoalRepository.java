package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Goal with JpaRepository.
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> { }
