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
public record ExerciseController(ExerciseService exerciseService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Exercise>>> getAllExercises() {
        return exerciseService.getAll();
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Exercise>> createExercise(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<DefaultResponse<Exercise>> getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getById(exerciseId);
    }

    @PatchMapping("/{exerciseId}")
    public ResponseEntity<DefaultResponse<Exercise>> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        return exerciseService.update(exerciseId, exercise);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<DefaultResponse<Void>> deleteExercise(@PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }
}
