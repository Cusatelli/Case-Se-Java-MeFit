package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.WorkoutService;
import com.noroff.mefit.data.model.Workout;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Workout")
@RequestMapping("/api/workout")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD, RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public class WorkoutController {
    private final WorkoutService workoutService;

    /**
     * Get all workouts through the exposed JPA Repository findAll method.
     * @return List of characters.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Workout>>> getAllWorkouts() {
        return this.workoutService.getAll();
    }

    /**
     * Create a new Workout through the exposed JPA Repository save method.
     * @param workout Character Model.
     * @return The created Workout Model.
     */
    @GetMapping("/{workoutId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Workout>> getWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.getById(workoutId);
    }

    /**
     * Update an existing Workout in database from its ID value, through the exposed JPA Repository save() method.
     * @param workout New Workout Model to overwrite the current Workout in database.
     * @param workoutId ID to overwrite in database.
     * @return The updated Workout Model.
     */
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Workout>> createWorkout(@RequestBody Workout workout) {
        return this.workoutService.create(workout);
    }

    @PatchMapping("/{workoutId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Workout>> updateWorkoutById(@PathVariable Long workoutId, @RequestBody Workout workout) {
        return this.workoutService.update(workoutId, workout);
    }

    /**
     * Delete a workout in database from ID input value, through exposed JPA Repository deleteById().
     * @param workoutId Workout ID to delete.
     * @return True if workout does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{workoutId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.delete(workoutId);
    }
}
