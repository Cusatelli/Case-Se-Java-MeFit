package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.ProgramWorkout;
import com.noroff.mefit.data.service.ProgramWorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Program Workout")
@RequestMapping("/api/program/workout")
public record ProgramWorkoutController(ProgramWorkoutService programWorkoutService) {
    @GetMapping
    public List<ProgramWorkout> getAllProgramWorkouts() {
        return programWorkoutService.getAll();
    }

    @PostMapping
    public ProgramWorkout createProgramWorkout(@RequestBody ProgramWorkout programWorkout) {
        return programWorkoutService.create(programWorkout);
    }

    @GetMapping("/{programWorkoutId}")
    public ProgramWorkout getProgramWorkoutById(@PathVariable Long programWorkoutId) {
        return programWorkoutService.getById(programWorkoutId);
    }

    @PatchMapping("/{programWorkoutId}")
    public ProgramWorkout updateProgramWorkout(@PathVariable Long programWorkoutId, @RequestBody ProgramWorkout programWorkout) {
        return programWorkoutService.update(programWorkoutId, programWorkout);
    }

    @DeleteMapping("/{programWorkoutId}")
    public Boolean deleteProgramWorkout(@PathVariable Long programWorkoutId) {
        return programWorkoutService.delete(programWorkoutId);
    }
}
