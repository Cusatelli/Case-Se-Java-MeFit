package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.WorkoutService;
import com.noroff.mefit.data.model.Workout;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Workout")
@RequestMapping("/api/workout")
public class WorkoutController {
    // Service implementing Repository extending JPARepository
    private final WorkoutService workoutService;

    /**
     * Get all workouts through the exposed JPA Repository findAll method.
     * @return List of characters.
     */
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Workout>>>getAllWorkouts() {
        return this.workoutService.getAll();
    }

    /**
     * Create a new Workout through the exposed JPA Repository save method.
     * @param workout Character Model.
     * @return The created Workout Model.
     */
    @PostMapping
    public ResponseEntity<DefaultResponse<Workout>> createWorkout(@RequestBody Workout workout) {
        return this.workoutService.create(workout);
    }

    /**
     * Find a specific Workout from its ID value through the exposed JPA Repository getById() method.
     * @param workoutId The Long ID to search for in Workout database.
     * @return The Workout Model found by getById() method.
     */
    @GetMapping("/{workoutId}")
    public ResponseEntity<DefaultResponse<Workout>> getWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.getById(workoutId);
    }

    /**
     * Update an existing Workout in database from its ID value, through the exposed JPA Repository save() method.
     * @param workout New Workout Model to overwrite the current Workout in database.
     * @param workoutId ID to overwrite in database.
     * @return The updated Workout Model.
     */
    @PatchMapping("/{workoutId}")
    public ResponseEntity<DefaultResponse<Workout>> updateWorkoutById(@PathVariable Long workoutId, @RequestBody Workout workout) {
        return this.workoutService.update(workoutId, workout);
    }

    /**
     * Delete a workout in database from ID input value, through exposed JPA Repository deleteById().
     * @param workoutId Workout ID to delete.
     * @return True if workout does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{workoutId}")
    public ResponseEntity<DefaultResponse<Void>> deleteWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.delete(workoutId);
    }
}
