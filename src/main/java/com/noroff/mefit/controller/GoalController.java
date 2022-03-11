package com.noroff.mefit.controller;

import com.noroff.mefit.model.Goal;
import com.noroff.mefit.service.GoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Goal")
@RequestMapping("/api/goal")
public record GoalController(GoalService goalService) {
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAll();
    }

    @PostMapping
    public Goal create(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @GetMapping("/{goalId}")
    public Goal getGoalById(@PathVariable Long goalId) {
        return goalService.getById(goalId);
    }

    @PatchMapping("/{goalId}")
    public Goal updateGoal(@RequestBody Goal goal, @PathVariable Long goalId) {
        return goalService.update(goal, goalId);
    }

    @DeleteMapping("/{goalId}")
    public Boolean deleteGoal(@PathVariable Long goalId) {
        return goalService.delete(goalId);
    }
}
