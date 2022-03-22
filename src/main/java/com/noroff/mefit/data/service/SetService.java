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

    public ResponseEntity<DefaultResponse<List<Set>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(setRepository.findAll())
        );
    }

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

    public ResponseEntity<DefaultResponse<Set>> create(Set set) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(setRepository.save(set))
        );
    }

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

    public void removeSetsInWorkout(Set set) {
        if(set == null) return;

        for (Workout workout : set.getWorkouts()) {
            workout.getSets().remove(set);
            workoutService.update(workout.getId(), workout);
        }
    }
}
