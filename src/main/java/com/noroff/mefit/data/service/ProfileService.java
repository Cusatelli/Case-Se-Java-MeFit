package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.*;
import com.noroff.mefit.data.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record ProfileService(
        ProfileRepository profileRepository,
        AddressService addressService,
        ProgramService programService,
        SetService setService,
        WorkoutService workoutService,
        GoalService goalService
) {
    private static final String TAG = Profile.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<Profile>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> getById(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, profileId))
            );
        }

        Profile profile = profileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profile)
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> create(Profile profile) {
        Profile savedProfile = profileRepository.save(profile);
        if(!profileRepository.existsById(savedProfile.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, savedProfile.getId()))
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(savedProfile)
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> update(Long profileId, Profile profile) {
        if (!profileRepository.existsById(profileId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, profileId))
            );
        }

        Profile dbProfile = profileRepository.findById(profileId).orElse(null);
        if(dbProfile == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        profile.setId(dbProfile.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(profile))
        );
    }

    public ResponseEntity<DefaultResponse<Void>> delete(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, profileId))
            );
        }

        profileRepository.deleteById(profileId);

        if(profileRepository.existsById(profileId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, profileId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> deleteAll(Profile profile) {
        if (profile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, null))
            );
        }

        if (!profileRepository.existsById(profile.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, profile.getId()))
            );
        }

        Address address = profile.getAddress();
        if(address != null) {
            ResponseEntity<DefaultResponse<Address>> response = addressService.delete(address.getId());
            if(!Objects.requireNonNull(response.getBody()).getSuccess()) {
                System.err.println(response.getBody().getError());
            }
        }

        List<Program> programs = profile.getPrograms();
        if(!programs.isEmpty()) {
            for (Program program : programs) {
                program.getProfiles().remove(profile);
                ResponseEntity<DefaultResponse<Program>> response = programService.update(program.getId(), program);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    System.err.println(response.getBody().getError());
                }
            }
        }

        List<Set> sets = profile.getSets();
        if(!programs.isEmpty()) {
            for (Set set : sets) {
                set.getProfiles().remove(profile);
                ResponseEntity<DefaultResponse<Set>> response = setService.update(set.getId(), set);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    System.err.println(response.getBody().getError());
                }
            }
        }

        List<Workout> workouts = profile.getWorkouts();
        if(!programs.isEmpty()) {
            for (Workout workout : workouts) {
                workout.getProfiles().remove(profile);
                ResponseEntity<DefaultResponse<Workout>> response = workoutService.update(workout.getId(), workout);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    System.err.println(response.getBody().getError());
                }
            }
        }

        List<Goal> goals = profile.getGoals();
        if(!programs.isEmpty()) {
            for (Goal goal : goals) {
                goal.getProfiles().remove(profile);
                ResponseEntity<DefaultResponse<Goal>> response = goalService.update(goal.getId(), goal);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    System.err.println(response.getBody().getError());
                }
            }
        }

        profileRepository.delete(profile);

        if(profileRepository.existsById(profile.getId())) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, profile.getId()))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
