package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Set;
import com.noroff.mefit.data.model.Workout;
import com.noroff.mefit.data.repository.SetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record SetService(
        SetRepository setRepository,
        WorkoutService workoutService
) {
    private static final String TAG = Set.class.getSimpleName();

    /**
     * Get all sets through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of sets.
     */
    public ResponseEntity<DefaultResponse<List<Set>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(setRepository.findAll())
        );
    }

    /**
     * Find a specific Set from its ID value through the exposed JPA Repository getById() method.
     * If set not found return correct response code,
     * if set has no content return correct response code
     * @param setId The Long ID to search for in Address database.
     * @return The Set Model found by getById() method.
     */
    public ResponseEntity<DefaultResponse<Set>> getById(Long setId) {
        if (!setRepository.existsById(setId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, setId))
            );
        }

        Set set = setRepository.findById(setId).orElse(null);
        if(set == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(set)
        );
    }

    /**
     * Create a new Set through the exposed JPA Repository save method.
     * using reasonable responses
     * @param set Set Model.
     * @return The created Set Model.
     */
    public ResponseEntity<DefaultResponse<Set>> create(Set set) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(setRepository.save(set))
        );
    }

    /**
     * Update an existing Set in database from its ID value, through the exposed JPA Repository save() method.
     * If set not found return correct response code,
     * if set has no content return correct response code
     * @param set New Set Model to overwrite the current Set in database.
     * @param setId ID to overwrite in database.
     * @return The updated Set Model.
     */
    public ResponseEntity<DefaultResponse<Set>> update(Long setId, Set set) {
        if (!setRepository.existsById(setId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, setId))
            );
        }

        Set dbSet = setRepository.findById(setId).orElse(null);
        if(dbSet == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        set.setId(dbSet.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(setRepository.save(set))
        );
    }

    /**
     * Delete a set in database from ID input value, through exposed JPA Repository deleteById().
     * If a set is not found return correct response code,
     * if a set has no content return correct response code
     * @param setId Set ID to delete.
     * @return True if set does not exist anymore. (Successful delete).
     */
    public ResponseEntity<DefaultResponse<Void>> delete(Long setId) {
        if (!setRepository.existsById(setId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, setId))
            );
        }

        removeSetsInWorkout(setRepository.findById(setId).orElse(null));
        setRepository.deleteById(setId);

        if(setRepository.existsById(setId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, setId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }

    /**
     * Remove a set in database from workout.
     * If a set is null/empty return false
     * @param set Set ID to remove.
     * @return True if set is removed from workout. (Successful delete).
     */
    public boolean removeSetsInWorkout(Set set) {
        if(set == null) return false;

        for (Workout workout : set.getWorkouts()) {
            workout.getSets().remove(set);
            workoutService.update(workout.getId(), workout);
        }

        return true;
    }
}
