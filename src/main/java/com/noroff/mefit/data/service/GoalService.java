package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.model.Workout;
import com.noroff.mefit.data.repository.GoalRepository;
import com.noroff.mefit.data.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record GoalService(GoalRepository goalRepository, ProfileRepository profileRepository) {
    private static final String TAG = Goal.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<Goal>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(goalRepository.findAll())
        );
    }

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

    public ResponseEntity<DefaultResponse<Goal>> create(Goal goal) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(goalRepository.save(goal))
        );
    }

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

    public ResponseEntity<DefaultResponse<Goal>> deleteWorkout(Long goalId, Workout workout) {
        Goal goal = Objects.requireNonNull(getById(goalId).getBody()).getPayload();
        if(goal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, goalId))
            );
        }

        goal.getWorkouts().remove(workout);
        return this.update(goalId, goal);
    }
}
