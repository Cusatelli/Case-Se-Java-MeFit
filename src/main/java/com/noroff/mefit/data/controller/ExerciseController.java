package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.ExerciseService;
import com.noroff.mefit.data.model.Exercise;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Exercise")
@RequestMapping("/api/exercise")
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
public class ExerciseController {
    private final ExerciseService exerciseService;

    /**
     * Get all exercises through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of exercises.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Exercise>>> getAllExercises() {
        return exerciseService.getAll();
    }

    /**
     * Find a specific Exercise from its ID value through the exposed JPA Repository getById() method.
     * @param exerciseId The Long ID to search for in Exercise database.
     * @return The Exercise Model found by getById() method.
     */
    @GetMapping("/{exerciseId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Exercise>> getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getById(exerciseId);
    }

    /**
     * Create a new Exercise through the exposed JPA Repository save method.
     * @param exercise Exercise Model.
     * @return The created Exercise Model.
     */
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Exercise>> createExercise(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    /**
     * Update an existing Exercise in database from its ID value, through the exposed JPA Repository save() method.
     * @param exercise New Exercise Model to overwrite the current Exercise in database.
     * @param exerciseId ID to overwrite in database.
     * @return The updated Exercise Model.
     */
    @PatchMapping("/{exerciseId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Exercise>> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        return exerciseService.update(exerciseId, exercise);
    }

    /**
     * Delete a exercise in database from ID input value, through exposed JPA Repository deleteById().
     * @param exerciseId Exercise ID to delete.
     * @return True if exercise does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{exerciseId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteExercise(@PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }
}
