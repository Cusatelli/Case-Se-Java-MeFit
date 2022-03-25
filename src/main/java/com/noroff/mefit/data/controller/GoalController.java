package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.service.GoalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Goal")
@AllArgsConstructor
@RequestMapping("/api/goal")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin("${server.cors.application_origin}")
public class GoalController {
    private final GoalService goalService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Goal>>> getAllGoals() {
        return goalService.getAll();
    }

    @GetMapping("/{goalId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Goal>> getGoalById(@PathVariable Long goalId) {
        return goalService.getById(goalId);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Goal>> createGoal(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @PatchMapping("/{goalId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Goal>> updateGoal(@PathVariable Long goalId, @RequestBody Goal goal) {
        return goalService.update(goalId, goal);
    }

    @DeleteMapping("/{goalId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteGoal(@PathVariable Long goalId) {
        return goalService.delete(goalId);
    }

}
