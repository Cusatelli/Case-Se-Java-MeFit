package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.ExerciseService;
import com.noroff.mefit.data.model.Exercise;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Exercise")
@RequestMapping("/api/exercise")
// Service implementing Repository extending JPARepository
public record ExerciseController(ExerciseService exerciseService) {

    /**
     * Get all exercises through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of exercises.
     */
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Exercise>>> getAllExercises() {
        return exerciseService.getAll();
    }

    /**
     * Create a new Exercise through the exposed JPA Repository save method.
     * @param exercise Exercise Model.
     * @return The created Exercise Model.
     */
    @PostMapping
    public ResponseEntity<DefaultResponse<Exercise>> createExercise(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    /**
     * Find a specific Exercise from its ID value through the exposed JPA Repository getById() method.
     * @param exerciseId The Long ID to search for in Exercise database.
     * @return The Exercise Model found by getById() method.
     */
    @GetMapping("/{exerciseId}")
    public ResponseEntity<DefaultResponse<Exercise>> getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getById(exerciseId);
    }

    /**
     * Update an existing Exercise in database from its ID value, through the exposed JPA Repository save() method.
     * @param exercise New Exercise Model to overwrite the current Exercise in database.
     * @param exerciseId ID to overwrite in database.
     * @return The updated Exercise Model.
     */
    @PatchMapping("/{exerciseId}")
    public ResponseEntity<DefaultResponse<Exercise>> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        return exerciseService.update(exerciseId, exercise);
    }

    /**
     * Delete a exercise in database from ID input value, through exposed JPA Repository deleteById().
     * @param exerciseId Exercise ID to delete.
     * @return True if exercise does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<DefaultResponse<Void>> deleteExercise(@PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }
}
