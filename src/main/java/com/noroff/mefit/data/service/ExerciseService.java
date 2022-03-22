package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Exercise;
import com.noroff.mefit.data.repository.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ExerciseService(ExerciseRepository exerciseRepository) {
    private static final String TAG = Exercise.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<Exercise>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(exerciseRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<Exercise>> getById(Long exerciseId) {
        if (!exerciseRepository.existsById(exerciseId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, exerciseId))
            );
        }

        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
        if(exercise == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(exercise)
        );
    }

    public ResponseEntity<DefaultResponse<Exercise>> create(Exercise exercise) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(exerciseRepository.save(exercise))
        );
    }

    public ResponseEntity<DefaultResponse<Exercise>> update(Long exerciseId, Exercise exercise) {
        if (!exerciseRepository.existsById(exerciseId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, exerciseId))
            );
        }

        Exercise dbExercise = exerciseRepository.findById(exerciseId).orElse(null);
        if(dbExercise == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        exercise.setId(dbExercise.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(exerciseRepository.save(exercise))
        );
    }

    public ResponseEntity<DefaultResponse<Void>> delete(Long exerciseId) {
        if (!exerciseRepository.existsById(exerciseId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, exerciseId))
            );
        }

        exerciseRepository.deleteById(exerciseId);

        if(exerciseRepository.existsById(exerciseId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, exerciseId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
