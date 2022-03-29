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

    /**
     * Get all exercises through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of exercises.
     */
    public ResponseEntity<DefaultResponse<List<Exercise>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(exerciseRepository.findAll())
        );
    }

    /**
     * Find a specific Exercise from its ID value through the exposed JPA Repository getById() method.
     * If exercise not found return correct response code,
     * if exercise has no content return correct response code
     * @param exerciseId The Long ID to search for in Exercise database.
     * @return The Exercise Model found by getById() method.
     */
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

    /**
     * Create a new Exercise through the exposed JPA Repository save method.
     * using reasonable responses
     * @param exercise Exercise Model.
     * @return The created Exercise Model.
     */
    public ResponseEntity<DefaultResponse<Exercise>> create(Exercise exercise) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(exerciseRepository.save(exercise))
        );
    }

    /**
     * Update an existing Exercise in database from its ID value, through the exposed JPA Repository save() method.
     * If exercise not found return correct response code,
     * if exercise has no content return correct response code
     * @param exercise New Exercise Model to overwrite the current Exercise in database.
     * @param exerciseId ID to overwrite in database.
     * @return The updated Exercise Model.
     */
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

    /**
     * Delete an exercise in database from ID input value, through exposed JPA Repository deleteById().
     * If exercise not found return correct response code,
     * if exercise has no content return correct response code
     * @param exerciseId Exercise ID to delete.
     * @return True if exercise does not exist anymore. (Successful delete).
     */
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
