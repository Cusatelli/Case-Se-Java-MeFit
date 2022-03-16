package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.GoalWorkout;
import com.noroff.mefit.data.service.GoalWorkoutService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Goal Workout")
@RequestMapping("/api/goal/workout")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin("${server.cors.application_origin}")
public record GoalWorkoutController(GoalWorkoutService goalWorkoutService) {
    @GetMapping
    public List<GoalWorkout> getAllGoals() {
        return goalWorkoutService.getAll();
    }

    @PostMapping
    public GoalWorkout create(@RequestBody GoalWorkout goalWorkout) {
        return goalWorkoutService.create(goalWorkout);
    }

    @GetMapping("/{goalWorkoutId}")
    public GoalWorkout getGoalById(@PathVariable Long goalWorkoutId) {
        return goalWorkoutService.getById(goalWorkoutId);
    }

    @PatchMapping("/{goalWorkoutId}")
    public GoalWorkout updateGoal(@RequestBody GoalWorkout goalWorkout, @PathVariable Long goalWorkoutId) {
        return goalWorkoutService.update(goalWorkout, goalWorkoutId);
    }

    @DeleteMapping("/{goalWorkoutId}")
    public Boolean deleteGoal(@PathVariable Long goalWorkoutId) {
        return goalWorkoutService.delete(goalWorkoutId);
    }
}
