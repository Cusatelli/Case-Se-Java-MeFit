package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.GoalWorkout;
import com.noroff.mefit.data.repository.GoalWorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record GoalWorkoutService(GoalWorkoutRepository goalWorkoutRepository) {
    public List<GoalWorkout> getAll() {
        return goalWorkoutRepository.findAll();
    }

    public GoalWorkout create(GoalWorkout goalWorkout) {
        return goalWorkoutRepository.save(goalWorkout);
    }

    public GoalWorkout getById(Long goalWorkoutId) {
        if (!goalWorkoutRepository.existsById(goalWorkoutId)) {
            return null;
        }

        return goalWorkoutRepository.findById(goalWorkoutId).orElse(null);
    }

    public GoalWorkout update(GoalWorkout goalWorkout, Long goalWorkoutId) {
        if (!goalWorkoutRepository.existsById(goalWorkoutId)) {
            return null;
        }

        GoalWorkout revGoalWorkout = goalWorkoutRepository.findById(goalWorkoutId).orElse(null);
        if(revGoalWorkout == null) {
            return null;
        }

        goalWorkout.setId(revGoalWorkout.getId());
        return goalWorkoutRepository.save(goalWorkout);
    }

    public Boolean delete(Long goalWorkoutId) {
        if (!goalWorkoutRepository.existsById(goalWorkoutId)) {
            return false;
        }

        goalWorkoutRepository.deleteById(goalWorkoutId);
        return !goalWorkoutRepository.existsById(goalWorkoutId);
    }
}
