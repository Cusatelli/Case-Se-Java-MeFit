package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.*;
import com.noroff.mefit.data.repository.SetRepository;
import com.noroff.mefit.data.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public record WorkoutService(
        WorkoutRepository workoutRepository,
        ProgramService programService,
        SetRepository setRepository,
        GoalService goalService
) {
    private static final String TAG = Workout.class.getSimpleName();

    /**
     * Get all workouts through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of workouts.
     */
    public ResponseEntity<DefaultResponse<List<Workout>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(workoutRepository.findAll())
        );
    }

    /**
     * Find a specific Workout from its ID value through the exposed JPA Repository getById() method.
     * If workout not found return correct response code,
     * if workout has no content return correct response code
     * @param workoutId The Long ID to search for in Workout database.
     * @return The Workout Model found by getById() method.
     */
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

    /**
     * Create a new Workout through the exposed JPA Repository save method.
     * using reasonable responses.
     * If setId is less than zero or equal to zero,
     * add a set workout and save.
     * Then save workout and set to repository.
     * @param workout Workout Model.
     * @return The created Workout Model.
     */
    public ResponseEntity<DefaultResponse<Workout>> create(Workout workout) {
        List<Set> sets = new ArrayList<>();
        for (Set set : workout.getSets()) {
            if(set.getId() <= 0) {
                set = setRepository.save(set);
            }
            sets.add(set);
        }
        workout.setSets(sets);
        setRepository.saveAll(workout.getSets());

        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(workoutRepository.save(workout))
        );
    }

    /**
     * Update an existing Workout in database from its ID value, through the exposed JPA Repository save() method.
     * If workout not found return correct response code,
     * if workout has no content return correct response code
     * If setId doesn't exist add set,
     * then add workout and save.
     * @param workout New Workout Model to overwrite the current Workout in database.
     * @param workoutId ID to overwrite in database.
     * @return The updated Workout Model.
     */
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

        List<Set> sets = new ArrayList<>();
        for (Set set : workout.getSets()) {
            if(set.getId() <= 0) {
                set = setRepository.save(set);
            }
            sets.add(set);
        }
        workout.setSets(sets);
        setRepository.saveAll(workout.getSets());

        workout.setId(dbWorkout.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(workoutRepository.save(workout))
        );
    }

    /**
     * Delete a workout in database from ID input value, through exposed JPA Repository deleteById().
     * If workout not found return correct response code,
     * if workout has no content return correct response code,
     * Look for program and goal linked to workout and remove,
     * @param workoutId Workout ID to delete.
     * @return Response no content. (Successful delete).
     */
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
