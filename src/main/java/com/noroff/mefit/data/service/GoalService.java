package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.repository.GoalRepository;
import com.noroff.mefit.data.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record GoalService(GoalRepository goalRepository, ProfileRepository profileRepository) {
    private static final String TAG = Goal.class.getSimpleName();

    /**
     * Get all goals through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of goals.
     */
    public ResponseEntity<DefaultResponse<List<Goal>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(goalRepository.findAll())
        );
    }

    /**
     * Find a specific Goal from its ID value through the exposed JPA Repository getById() method.
     * If goal not found return correct response code,
     * if goal has no content return correct response code
     * @param goalId The Long ID to search for in Goal database.
     * @return The Goal Model found by getById() method.
     */
    public ResponseEntity<DefaultResponse<Goal>> getById(Long goalId) {
        if (!goalRepository.existsById(goalId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, goalId))
            );
        }

        Goal goal = goalRepository.findById(goalId).orElse(null);
        if(goal == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(goal)
        );
    }

    /**
     * Create a new Goal through the exposed JPA Repository save method.
     * using reasonable responses
     * @param goal Goal Model.
     * @return The created Goal Model.
     */
    public ResponseEntity<DefaultResponse<Goal>> create(Goal goal) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(goalRepository.save(goal))
        );
    }

    /**
     * Update an existing Goal in database from its ID value, through the exposed JPA Repository save() method.
     * If goal not found return correct response code,
     * if goal has no content return correct response code
     * @param goal New Goal Model to overwrite the current Goal in database.
     * @param goalId ID to overwrite in database.
     * @return The updated Goal Model.
     */
    public ResponseEntity<DefaultResponse<Goal>> update(Long goalId, Goal goal) {
        if (!goalRepository.existsById(goalId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, goalId))
            );
        }

        Goal dbGoal = goalRepository.findById(goalId).orElse(null);
        if(dbGoal == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        goal.setId(dbGoal.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(goalRepository.save(goal))
        );
    }

    /**
     * Delete a goal in database from ID input value, through exposed JPA Repository deleteById().
     * If goal not found return correct response code,
     * if goal has no content return correct response code
     * @param goalId Goal ID to delete.
     * @return True if goal does not exist anymore. (Successful delete).
     */
    public ResponseEntity<DefaultResponse<Void>> delete(Long goalId) {
        if (!goalRepository.existsById(goalId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, goalId))
            );
        }

        goalRepository.deleteById(goalId);

        if(goalRepository.existsById(goalId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, goalId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
