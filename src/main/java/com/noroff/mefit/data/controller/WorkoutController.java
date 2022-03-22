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
    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<DefaultResponse<List<Workout>>>getAllWorkouts() {
        return this.workoutService.getAll();
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Workout>> createWorkout(@RequestBody Workout workout) {
        return this.workoutService.create(workout);
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<DefaultResponse<Workout>> getWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.getById(workoutId);
    }

    @PatchMapping("/{workoutId}")
    public ResponseEntity<DefaultResponse<Workout>> updateWorkoutById(@PathVariable Long workoutId, @RequestBody Workout workout) {
        return this.workoutService.update(workoutId, workout);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<DefaultResponse<Void>> deleteWorkoutById(@PathVariable Long workoutId) {
        return this.workoutService.delete(workoutId);
    }
}
