package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.service.GoalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Goal")
@RequestMapping("/api/goal")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin("${server.cors.application_origin}")
public record GoalController(GoalService goalService) {
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAll();
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @GetMapping("/{goalId}")
    public Goal getGoalById(@PathVariable Long goalId) {
        return goalService.getById(goalId);
    }

    @PatchMapping("/{goalId}")
    public Goal updateGoal(@PathVariable Long goalId, @RequestBody Goal goal) {
        return goalService.update(goalId, goal);
    }

    @DeleteMapping("/{goalId}")
    public Boolean deleteGoal(@PathVariable Long goalId) {
        return goalService.delete(goalId);
    }

}
