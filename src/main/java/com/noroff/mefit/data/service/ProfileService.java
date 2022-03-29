package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.*;
import com.noroff.mefit.data.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * Get all profiles through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of profiles.
     */
    public ResponseEntity<DefaultResponse<List<Profile>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.findAll())
        );
    }

    /**
     * Find a specific Profile from its ID value through the exposed JPA Repository getById() method.
     * If profile not found return correct response code,
     * if profile has no content return correct response code
     * @param profileId The Long ID to search for in Profile database.
     * @return The Profile Model found by getById() method.
     */
    public ResponseEntity<DefaultResponse<Profile>> getById(Long profileId) {
        if (!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        Profile profile = profileRepository.findById(profileId).orElse(null);
        if(profile == null) { return RESPONSE_NO_CONTENT(); }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profile)
        );
    }

    /**
     * Create a new Profile through the exposed JPA Repository save method.
     * using reasonable responses
     * if profile has no content return correct response code
     * @param profile Profile Model.
     * @return The created Profile Model.
     */
    public ResponseEntity<DefaultResponse<Profile>> create(Profile profile) {
        Profile savedProfile = profileRepository.save(profile);
        if(!profileRepository.existsById(savedProfile.getId())) { return RESPONSE_NOT_FOUND(-1L); }

        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(savedProfile)
        );
    }

    /**
     * Update a profile Address in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * if profile has no content return correct response code
     * @param profile New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
    public ResponseEntity<DefaultResponse<Profile>> update(Long profileId, Profile profile) {
        if (!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        Profile dbProfile = profileRepository.findById(profileId).orElse(null);
        if(dbProfile == null) { return RESPONSE_NO_CONTENT(); }

        profile.setId(dbProfile.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(profile))
        );
    }

    /**
     * Update a profile Address in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * if profile has no content return correct response code,
     * if profile address doesn't exist add address to profile and save
     * @param address New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
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

    /**
     * Update a profile Goal in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * if profile has no content return correct response code,
     * if profile goal doesn't exist create goal in profile and save,
     * and if goal exist, update goal in profile.
     * @param goal New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
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

    /**
     * Update a profile Program in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * if profile program doesn't exist create program in profile and save,
     * and if program exist, update program in profile.
     * @param program New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
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

    /**
     * Update a profile Set in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * if profile set doesn't exist create set in profile and save,
     * and if set exist, update set in profile.
     * @param set New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
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

    /**
     * Update a list of profile Programs in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * loop through programs, if not exists in profile add
     * workouts and programs to profile.
     * @param programs New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
    public ResponseEntity<DefaultResponse<Profile>> updatePrograms(Long profileId, List<Program> programs) {
        Profile savedProfile = profileRepository.findById(profileId).orElse(null);
        if(savedProfile == null) { return RESPONSE_NOT_FOUND(profileId); }

        List<Program> savedPrograms = new ArrayList<>();
        for (Program workout : programs) {
            DefaultResponse<Program> savedProgram = programService.getById(workout.getId()).getBody();
            if (savedProgram != null) {
                savedPrograms.add(savedProgram.getPayload());
            }
        }

        for (Program program : savedPrograms) {
            if(!program.getProfiles().contains(savedProfile)) {
                program.getProfiles().add(savedProfile);
            }

            DefaultResponse<Program> response = programService.update(program.getId(), program).getBody();
            if (!Objects.requireNonNull(response).getSuccess()) {
                return ResponseEntity.status(HttpStatus.valueOf(response.getError().getStatus())).body(
                        new DefaultResponse<>(response.getError().getStatus(), response.getError().getMessage())
                );
            }
        }
        savedProfile.setPrograms(savedPrograms);

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(savedProfile))
        );
    }

    /**
     * Update a list of profile Workouts in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * loop through workouts, if not exists in profile add
     * workouts to profile.
     * @param workouts New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
    public ResponseEntity<DefaultResponse<Profile>> updateWorkouts(Long profileId, List<Workout> workouts) {
        Profile savedProfile = profileRepository.findById(profileId).orElse(null);
        if(savedProfile == null) { return RESPONSE_NOT_FOUND(profileId); }

        List<Workout> savedWorkouts = new ArrayList<>();
        for (Workout workout : workouts) {
            DefaultResponse<Workout> savedWorkout = workoutService.getById(workout.getId()).getBody();
            if (savedWorkout != null) {
                savedWorkouts.add(savedWorkout.getPayload());
            }
        }

        for (Workout workout : savedWorkouts) {
            if(!workout.getProfiles().contains(savedProfile)) {
                workout.getProfiles().add(savedProfile);
            }

            DefaultResponse<Workout> response = workoutService.update(workout.getId(), workout).getBody();
            if (!Objects.requireNonNull(response).getSuccess()) {
                return ResponseEntity.status(HttpStatus.valueOf(response.getError().getStatus())).body(
                        new DefaultResponse<>(response.getError().getStatus(), response.getError().getMessage())
                );
            }
//            workouts.remove(workout);
//            workouts.add(response.getPayload());
        }
        savedProfile.setWorkouts(savedWorkouts);

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(savedProfile))
        );
    }

    /**
     * Update a profile Workout in database from its ID value, through the exposed JPA Repository save() method.
     * If profile not found return correct response code,
     * if profile workout doesn't exist create workout in profile and save,
     * and if workout exist, update workout in profile.
     * @param workout New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     * all other calls returns bad request.
     */
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

    /**
     * Delete everything related to Profile in database such as
     * workout, set, program, goal and address.
     * If profile not found return correct response code,
     * if profile has no content return correct response code
     * @param profile Profile ID to delete.
     * @return response no content if no profile exist, (Successful delete).
     */
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

    /**
     * Remove a workout in database from profile.
     * if profile not found return correct response code.
     * @param profileId Profile ID to delete.
     * @param workoutId New Profile Model to overwrite the current Profile in database.
     * @return The updated Profile Model.
     */
    public ResponseEntity<DefaultResponse<Profile>> removeWorkout(Long profileId, Long workoutId) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }

        Profile savedProfile = profileResponse.getBody().getPayload();
        List<Workout> workouts = savedProfile.getWorkouts();
        Workout workout = workoutService.getById(workoutId).getBody().getPayload();
        if(workouts.contains(workout)) {
            workout.getProfiles().remove(savedProfile);
            workouts.remove(workout);
            workoutService.update(workout.getId(), workout);
        }

        savedProfile.setWorkouts(workouts);

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(savedProfile))
        );
    }

    /**
     * Remove a program in database from profile.
     * if profile not found return correct response code.
     * @param profileId Profile ID to delete.
     * @param programId New Profile Model to overwrite the current Profile in database.
     * @return The updated Profile Model.
     */
    public ResponseEntity<DefaultResponse<Profile>> removeProgram(Long profileId, Long programId) {
        if(!profileRepository.existsById(profileId)) { return RESPONSE_NOT_FOUND(profileId); }

        ResponseEntity<DefaultResponse<Profile>> profileResponse = getById(profileId);
        if(profileResponse.getBody() == null || !profileResponse.getBody().getSuccess()) { return RESPONSE_NOT_FOUND(profileId); }

        Profile savedProfile = profileResponse.getBody().getPayload();
        List<Program> programs = savedProfile.getPrograms();
        Program program = programService.getById(programId).getBody().getPayload();
        if(programs.contains(program)) {
            program.getProfiles().remove(savedProfile);
            programs.remove(program);
            programService.update(program.getId(), program);
        }

        savedProfile.setPrograms(programs);

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(profileRepository.save(savedProfile))
        );
    }

    /**
     * Response message for profile and status code
     * @return Human-readable no content message.
     */
    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_NO_CONTENT() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }

    /**
     * Response message for profile and status code
     * @param profileId Profile ID to find
     * @return Human-readable found profile message.
     */
    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_FOUND(Long profileId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, profileId))
        );
    }

    /**
     * Response message for profile and status code
     * @param profileId to refer to
     * @return Human-readable not found message.
     */
    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_NOT_FOUND(Long profileId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, profileId))
        );
    }

    /**
     * Response message for profile and status code
     * @return Human-readable bad request (status code 400) message.
     */
    private static ResponseEntity<DefaultResponse<Profile>> RESPONSE_BAD_REQUEST() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), DefaultResponse.BAD_REQUEST(TAG))
        );
    }
}
