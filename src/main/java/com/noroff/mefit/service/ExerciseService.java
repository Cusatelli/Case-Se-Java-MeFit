package com.noroff.mefit.service;

import com.noroff.mefit.model.Exercise;
import com.noroff.mefit.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ExerciseService(ExerciseRepository exerciseRepository) {

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise create(Exercise user) {
        return exerciseRepository.save(user);
    }

    public Exercise getById(Long userId) {
        if (!exerciseRepository.existsById((userId))) {
            return null;
        }

        return exerciseRepository.findById(userId).orElse(null);
    }

    public Exercise update(Exercise exercise, Long exerciseId) {
        if (!exerciseRepository.existsById((exerciseId))) {
            return null;
        }

        exercise.setId(exerciseRepository.getById(exerciseId).getId());
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