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
// Service implementing Repository extending JPARepository
public record GoalController(GoalService goalService) {

    /**
     * Get all goals through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of goals.
     */
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Goal>>> getAllGoals() {
        return goalService.getAll();
    }

    /**
     * Create a new Goal through the exposed JPA Repository save method.
     * @param goal Goal Model.
     * @return The created Goal Model.
     */
    @PostMapping
    public ResponseEntity<DefaultResponse<Goal>> createGoal(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    /**
     * Find a specific Goal from its ID value through the exposed JPA Repository getById() method.
     * @param goalId The Long ID to search for in Goal database.
     * @return The Character Model found by getById() methody.
     */
    @GetMapping("/{goalId}")
    public ResponseEntity<DefaultResponse<Goal>> getGoalById(@PathVariable Long goalId) {
        return goalService.getById(goalId);
    }

    /**
     * Update an existing Goal in database from its ID value, through the exposed JPA Repository save() method.
     * @param goal New Goal Model to overwrite the current Goal in database.
     * @param goalId ID to overwrite in database.
     * @return The updated goal Model.
     */
    @PatchMapping("/{goalId}")
    public ResponseEntity<DefaultResponse<Goal>> updateGoal(@PathVariable Long goalId, @RequestBody Goal goal) {
        return goalService.update(goalId, goal);
    }

    /**
     * Delete a goal in database from ID input value, through exposed JPA Repository deleteById().
     * @param goalId Goal ID to delete.
     * @return True if goal does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{goalId}")
    public ResponseEntity<DefaultResponse<Void>> deleteGoal(@PathVariable Long goalId) {
        return goalService.delete(goalId);
    }

}
