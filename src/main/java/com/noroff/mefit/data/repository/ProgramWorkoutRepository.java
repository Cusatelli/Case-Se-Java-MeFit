package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.ProgramWorkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramWorkoutRepository extends JpaRepository<ProgramWorkout, Long> {
}
