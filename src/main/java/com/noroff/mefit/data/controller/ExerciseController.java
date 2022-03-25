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

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Exercise>>> getAllExercises() {
        return exerciseService.getAll();
    }

    @GetMapping("/{exerciseId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Exercise>> getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getById(exerciseId);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Exercise>> createExercise(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    @PatchMapping("/{exerciseId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Exercise>> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        return exerciseService.update(exerciseId, exercise);
    }

    @DeleteMapping("/{exerciseId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteExercise(@PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }
}
