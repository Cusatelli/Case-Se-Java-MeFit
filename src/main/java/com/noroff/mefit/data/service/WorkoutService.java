package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.model.Workout;
import com.noroff.mefit.data.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record WorkoutService(
        WorkoutRepository workoutRepository,
        ProgramService programService,
        GoalService goalService
) {
    private static final String TAG = Workout.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<Workout>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(workoutRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<Workout>> getById(Long workoutId) {
        if (!workoutRepository.existsById(workoutId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, workoutId))
            );
        }

        Workout workout = workoutRepository.findById(workoutId).orElse(null);
        if(workout == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(workout)
        );
    }

    public ResponseEntity<DefaultResponse<Workout>> create(Workout workout) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(workoutRepository.save(workout))
        );
    }

    public ResponseEntity<DefaultResponse<Workout>> update(Long workoutId, Workout workout) {
        if (!workoutRepository.existsById(workoutId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, workoutId))
            );
        }

        Workout dbWorkout = workoutRepository.findById(workoutId).orElse(null);
        if(dbWorkout == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        workout.setId(dbWorkout.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(workoutRepository.save(workout))
        );
    }

    public ResponseEntity<DefaultResponse<Void>> delete(Long workoutId) {
        if (!workoutRepository.existsById(workoutId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, workoutId))
            );
        }

        Workout workout = workoutRepository.findById(workoutId).orElse(null);
        if(workout != null) {
            for (Program program : workout.getPrograms()) {
                program.getWorkouts().remove(workout);
                ResponseEntity<DefaultResponse<Program>> response = programService.update(program.getId(), program);
                if(!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    System.err.println(response.getBody().getError());
                }
            }

            for (Goal goal : workout.getGoals()) {
                goal.getWorkouts().remove(workout);
                ResponseEntity<DefaultResponse<Goal>> response = goalService.update(goal.getId(), goal);
                if(!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    System.err.println(response.getBody().getError());
                }
            }
        }

        workoutRepository.deleteById(workoutId);

        if(workoutRepository.existsById(workoutId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, workoutId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
