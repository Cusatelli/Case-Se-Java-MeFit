package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.service.GoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Goal")
@RequestMapping("/api/goal")
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD,
                RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public record GoalController(GoalService goalService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Goal>>> getAllGoals() {
        return goalService.getAll();
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Goal>> createGoal(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<DefaultResponse<Goal>> getGoalById(@PathVariable Long goalId) {
        return goalService.getById(goalId);
    }

    @PatchMapping("/{goalId}")
    public ResponseEntity<DefaultResponse<Goal>> updateGoal(@PathVariable Long goalId, @RequestBody Goal goal) {
        return goalService.update(goalId, goal);
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<DefaultResponse<Void>> deleteGoal(@PathVariable Long goalId) {
        return goalService.delete(goalId);
    }

}
