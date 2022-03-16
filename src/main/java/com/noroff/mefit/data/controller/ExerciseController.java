package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.service.ExerciseService;
import com.noroff.mefit.data.model.Exercise;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Exercise")
@RequestMapping("/api/exercise")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin("${server.cors.application_origin}")
public record ExerciseController(ExerciseService exerciseService) {
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAll();
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    @GetMapping("/{exerciseId}")
    public Exercise getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getById(exerciseId);
    }

    @PatchMapping("/{exerciseId}")
    public Exercise updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        return exerciseService.update(exerciseId, exercise);
    }

    @DeleteMapping("/{exerciseId}")
    public Boolean deleteExercise(@PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }
}
