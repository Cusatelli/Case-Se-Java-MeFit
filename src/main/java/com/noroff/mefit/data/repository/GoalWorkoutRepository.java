package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.GoalWorkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalWorkoutRepository extends JpaRepository<GoalWorkout, Long> {
}
