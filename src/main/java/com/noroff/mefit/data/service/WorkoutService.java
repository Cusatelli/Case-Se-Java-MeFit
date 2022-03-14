package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Workout;
import com.noroff.mefit.data.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record WorkoutService(WorkoutRepository workoutRepository) {
    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }

    public Workout getById(Long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    public Workout add(Workout workout) {
        if(workout.getComplete() == null) { workout.setComplete(false); }
        return this.workoutRepository.saveAndFlush(workout);
    }

    public Workout add(Workout workout, String workoutType) {
        if(workoutType != null) { workout.setType(workoutType); }
        return this.add(workout);
    }

    public Workout update(Long workoutId, Workout workout) {
        if(!existsById(workoutId)) { return null; }

        workout.setId(workoutId); // Make sure the ID matches.
        return this.workoutRepository.saveAndFlush(workout);
    }

    public Boolean delete(Long workoutId) {
        if(!existsById(workoutId)) { return false; } // Check if exists before delete.

        this.workoutRepository.deleteById(workoutId);
        return !existsById(workoutId); // Check if exists after delete and return opposite.
    }

    /**
     * Helper Method to shorten the written statement to check if an Object with an ID exists.
     * @param id value to search for in database.
     * @return true if value lives in database / false if value does not exist.
     */
    private Boolean existsById(Long id) {
        return this.workoutRepository.existsById(id);
    }
}
