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
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD, RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public class GoalController {
    private final GoalService goalService;

    /**
     * Get all goals through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of goals.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Goal>>> getAllGoals() {
        return goalService.getAll();
    }

    /**
     * Find a specific Goal from its ID value through the exposed JPA Repository getById() method.
     * @param goalId The Long ID to search for in Goal database.
     * @return The Character Model found by getById() methody.
     */
    @GetMapping("/{goalId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Goal>> getGoalById(@PathVariable Long goalId) {
        return goalService.getById(goalId);
    }

    /**
     * Create a new Goal through the exposed JPA Repository save method.
     * @param goal Goal Model.
     * @return The created Goal Model.
     */
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Goal>> createGoal(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    /**
     * Update an existing Goal in database from its ID value, through the exposed JPA Repository save() method.
     * @param goal New Goal Model to overwrite the current Goal in database.
     * @param goalId ID to overwrite in database.
     * @return The updated goal Model.
     */
    @PatchMapping("/{goalId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Goal>> updateGoal(@PathVariable Long goalId, @RequestBody Goal goal) {
        return goalService.update(goalId, goal);
    }

    /**
     * Delete a goal in database from ID input value, through exposed JPA Repository deleteById().
     * @param goalId Goal ID to delete.
     * @return True if goal does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{goalId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteGoal(@PathVariable Long goalId) {
        return goalService.delete(goalId);
    }

}
