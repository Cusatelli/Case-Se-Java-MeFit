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
        if (!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        Profile profile = profileRepository.findById(profileId).orElse(null);
        if(profile == null) { return RESPONSE_NO_CONTENT(); }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profile)
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> create(Profile profile) {
        Profile savedProfile = profileRepository.save(profile);
        if(!profileRepository.existsById(savedProfile.getId())) { return RESPONSE_NOT_FOUND(-1L); }

        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(savedProfile)
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> update(Long profileId, Profile profile) {
        if (!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        Profile dbProfile = profileRepository.findById(profileId).orElse(null);
        if(dbProfile == null) { return RESPONSE_NO_CONTENT(); }

        profile.setId(dbProfile.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(profile))
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> updateAddress(Long profileId, Address address) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }
        Profile savedProfile = profileResponse.getBody().getPayload();

        if(savedProfile.getAddress() == null) {
            address.setProfile(savedProfile);
            ResponseEntity<DefaultResponse<Address>> response = addressService.create(address);
            if(response.getBody() != null && response.getBody().getSuccess()) {
                Address savedAddress = response.getBody().getPayload();
                savedProfile.setAddress(savedAddress);
            }
        } else {
            address.setId(savedProfile.getAddress().getId());
            address.setProfile(savedProfile);
            ResponseEntity<DefaultResponse<Address>> response = addressService.update(address.getId(), address);
            if(response.getBody() != null && response.getBody().getSuccess()) {
                Address savedAddress = response.getBody().getPayload();
                savedProfile.setAddress(savedAddress);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(savedProfile))
        );
    }

    public ResponseEntity<DefaultResponse<Profile>> updateGoal(Long profileId, Goal goal) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }
        Profile savedProfile = profileResponse.getBody().getPayload();

        List<Goal> goals = savedProfile.getGoals();
        // if empty => create
        ResponseEntity<DefaultResponse<Goal>> response;
        if(goals.isEmpty()) {
            goal.getProfiles().add(savedProfile);
            response = goalService.create(goal);
            if(response.getBody() != null && response.getBody().getSuccess()) {
                goals.add(response.getBody().getPayload());
                savedProfile.setGoals(goals);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DefaultResponse<>(profileRepository.save(savedProfile))
                );
            }
        } else {
            // if exists => update
            for (Goal savedGoal : goals) {
                goal.setId(savedGoal.getId());
                goal.setProfiles(savedGoal.getProfiles());
                response = goalService.update(goal.getId(), goal);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    return ResponseEntity.status(HttpStatus.valueOf(response.getBody().getError().getStatus())).body(
                            new DefaultResponse<>(response.getBody().getError().getStatus(), response.getBody().getError().getMessage())
                    );
                }
                goals.remove(savedGoal);
                goals.add(response.getBody().getPayload());
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new DefaultResponse<>(profileRepository.save(savedProfile))
            );
        }

        return RESPONSE_BAD_REQUEST();
    }

    public ResponseEntity<DefaultResponse<Profile>> updateProgram(Long profileId, Program program) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }
        Profile savedProfile = profileResponse.getBody().getPayload();

        List<Program> programs = savedProfile.getPrograms();
        // if empty => create
        ResponseEntity<DefaultResponse<Program>> response;
        if(programs.isEmpty()) {
            program.getProfiles().add(savedProfile);
            response = programService.create(program);
            if(response.getBody() != null && response.getBody().getSuccess()) {
                programs.add(response.getBody().getPayload());
                savedProfile.setPrograms(programs);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DefaultResponse<>(profileRepository.save(savedProfile))
                );
            }
        } else {
            // if exists => update
            for (Program savedProgram : programs) {
                program.setId(savedProgram.getId());
                program.setProfiles(savedProgram.getProfiles());
                response = programService.update(program.getId(), program);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    return ResponseEntity.status(HttpStatus.valueOf(response.getBody().getError().getStatus())).body(
                            new DefaultResponse<>(response.getBody().getError().getStatus(), response.getBody().getError().getMessage())
                    );
                }
                programs.remove(savedProgram);
                programs.add(response.getBody().getPayload());
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new DefaultResponse<>(profileRepository.save(savedProfile))
            );
        }

        return RESPONSE_BAD_REQUEST();
    }

    // TODO: Be able to add multiple sets (applies to all updateXYZ below):
    public ResponseEntity<DefaultResponse<Profile>> updateSet(Long profileId, Set set) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }
        Profile savedProfile = profileResponse.getBody().getPayload();

        List<Set> sets = savedProfile.getSets();
        // if empty => create
        ResponseEntity<DefaultResponse<Set>> response;
        if(sets.isEmpty()) {
            set.getProfiles().add(savedProfile);
            response = setService.create(set);
            if(response.getBody() != null && response.getBody().getSuccess()) {
                sets.add(response.getBody().getPayload());
                savedProfile.setSets(sets);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DefaultResponse<>(profileRepository.save(savedProfile))
                );
            }
        } else {
            // if exists => update
            for (Set savedSet : sets) {
                set.setId(savedSet.getId());
                set.setProfiles(savedSet.getProfiles());
                response = setService.update(set.getId(), set);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    return ResponseEntity.status(HttpStatus.valueOf(response.getBody().getError().getStatus())).body(
                            new DefaultResponse<>(response.getBody().getError().getStatus(), response.getBody().getError().getMessage())
                    );
                }
                sets.remove(savedSet);
                sets.add(response.getBody().getPayload());
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new DefaultResponse<>(profileRepository.save(savedProfile))
            );
        }

        return RESPONSE_BAD_REQUEST();
    }

    public ResponseEntity<DefaultResponse<Profile>> updateWorkout(Long profileId, Workout workout) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }

        Profile savedProfile = profileResponse.getBody().getPayload();

        List<Workout> workouts = savedProfile.getWorkouts();
        // if empty => create
        ResponseEntity<DefaultResponse<Workout>> response;
        if(workouts.isEmpty()) {
            workout.getProfiles().add(savedProfile);
            response = workoutService.create(workout);
            if(response.getBody() != null && response.getBody().getSuccess()) {
                workouts.add(response.getBody().getPayload());
                savedProfile.setWorkouts(workouts);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DefaultResponse<>(profileRepository.save(savedProfile))
                );
            }
        } else {
            // if exists => update
            for (Workout savedWorkout : workouts) {
                workout.setId(savedWorkout.getId());
                workout.setProfiles(savedWorkout.getProfiles());
                response = workoutService.update(workout.getId(), workout);
                if (!Objects.requireNonNull(response.getBody()).getSuccess()) {
                    return ResponseEntity.status(HttpStatus.valueOf(response.getBody().getError().getStatus())).body(
                            new DefaultResponse<>(response.getBody().getError().getStatus(), response.getBody().getError().getMessage())
                    );
                }
                workouts.remove(savedWorkout);
                workouts.add(response.getBody().getPayload());
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new DefaultResponse<>(profileRepository.save(savedProfile))
            );
        }

        return RESPONSE_BAD_REQUEST();
    }

    public ResponseEntity<DefaultResponse<Profile>> delete(Long profileId) {
        if (!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        profileRepository.deleteById(profileId);

        if(profileRepository.existsById(profileId)) { return RESPONSE_FOUND(profileId); }

        return RESPONSE_NO_CONTENT();
    }

    public ResponseEntity<DefaultResponse<Profile>> deleteAll(Profile profile) {
        if (profile == null) { return RESPONSE_NOT_FOUND(-1L); }

        if (!profileRepository.existsById(profile.getId())) { return RESPONSE_NOT_FOUND(profile.getId()); }

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

        if(profileRepository.existsById(profile.getId())) { return RESPONSE_FOUND(profile.getId()); }

        return RESPONSE_NO_CONTENT();
    }
    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_NO_CONTENT() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }

    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_FOUND(Long profileId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, profileId))
        );
    }

    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_NOT_FOUND(Long profileId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, profileId))
        );
    }

    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_BAD_REQUEST() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), DefaultResponse.BAD_REQUEST(TAG))
        );
    }
}
