package com.noroff.mefit.controller;

import com.noroff.mefit.model.Exercise;
import com.noroff.mefit.service.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Exercise")
@RequestMapping("/api/exercise")
public record ExerciseController(ExerciseService exerciseService) {

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    @GetMapping("/{exerciseId}")
    public Exercise getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getById(exerciseId);
    }

    @PatchMapping("/{exerciseId}")
    public Exercise update(@RequestBody Exercise exercise, @PathVariable Long exerciseId) {
        return exerciseService.update(exercise, exerciseId);
    }

    @DeleteMapping("/{exerciseId}")
    public Boolean delete(@PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }
}