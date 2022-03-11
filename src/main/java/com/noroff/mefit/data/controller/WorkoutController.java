package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.service.WorkoutService;
import com.noroff.mefit.data.model.Workout;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Workout")
@RequestMapping("/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return this.workoutService.getAll();
    }

    @PostMapping
    public Workout postWorkout(@RequestBody Workout workout) {
        return this.workoutService.add(workout);
    }

    @GetMapping("/{workoutId}")
    public Workout getWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.getById(workoutId);
    }

    @PatchMapping("/{workoutId}")
    public Workout patchWorkoutById(@PathVariable Long workoutId, @RequestBody Workout workout) {
        return this.workoutService.update(workoutId, workout);
    }

    @DeleteMapping("/{workoutId}")
    public Boolean deleteWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.delete(workoutId);
    }
}
