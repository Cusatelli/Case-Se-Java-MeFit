package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Exercise;
import com.noroff.mefit.data.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ExerciseService(ExerciseRepository exerciseRepository) {
    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    public Exercise create(Exercise user) {
        return exerciseRepository.save(user);
    }

    public Exercise getById(Long userId) {
        if (!exerciseRepository.existsById(userId)) {
            return null;
        }

        return exerciseRepository.findById(userId).orElse(null);
    }

    public Exercise update(Long exerciseId, Exercise exercise) {
        if (!exerciseRepository.existsById(exerciseId)) {
            return null;
        }

        Exercise prevExercise = exerciseRepository.findById(exerciseId).orElse(null);
        if(prevExercise == null) {
            return null;
        }

        exercise.setId(prevExercise.getId());
        return exerciseRepository.save(exercise);
    }

    public Boolean delete(Long userId) {
        if (!exerciseRepository.existsById(userId)) {
            return false;
        }

        exerciseRepository.deleteById(userId);
        return !exerciseRepository.existsById(userId);
    }
}