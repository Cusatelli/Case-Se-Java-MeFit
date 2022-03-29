package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.*;
import com.noroff.mefit.data.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Profile")
@RequestMapping("/api/profile")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD, RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public class ProfileController {
    private final ProfileService profileService;

    /**
     * Get everything in profile through the exposed JPA Repository findAll method.
     * @return List of profiles.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Profile>>> getAllProfiles() {
        return profileService.getAll();
    }

    /**
     * Find a specific PRofile from its ID value through the exposed JPA Repository getById() method.
     * @param profileId The Long ID to search for in Profile database.
     * @return The Profile Model found by getById() method.
     */
    @GetMapping("/{profileId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> getProfileById(@PathVariable Long profileId) {
        return profileService.getById(profileId);
    }

    /**
     * Update an existing Profile in database from its ID value, through the exposed JPA Repository save() method.
     * @param profile New Profile Model to overwrite the current Profile in database.
     * @param profileId ID to overwrite in database.
     * @return The updated Profile Model.
     */
    @PatchMapping("/{profileId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfile(@PathVariable Long profileId, @RequestBody Profile profile) {
        return profileService.update(profileId, profile);
    }

    /**
     * Update an existing Address in profile
     * @param address to overwrite the current Address in the Profile.
     * @param profileId related to where we're editing the address.
     * @return The updated address- and profileID.
     */
    @PatchMapping("/{profileId}/address")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileAddress(@PathVariable Long profileId, @RequestBody Address address) {
        return profileService.updateAddress(profileId, address);
    }

    /**
     * Update an existing Goal in profile
     * @param goal to overwrite the current Goal in the Profile.
     * @param profileId related to where we're editing the goal.
     * @return The updated goal- and profileID.
     */
    @PatchMapping("/{profileId}/goal")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileGoal(@PathVariable Long profileId, @RequestBody Goal goal) {
        return profileService.updateGoal(profileId, goal);
    }

    /**
     * Update an existing Program in profile
     * @param program to overwrite the current Program in the Profile.
     * @param profileId related to where we're editing the program.
     * @return The updated program- and profileID.
     */
    @PatchMapping("/{profileId}/program")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileProgram(@PathVariable Long profileId, @RequestBody Program program) {
        return profileService.updateProgram(profileId, program);
    }

    /**
     * Update an existing Set in profile
     * @param set to overwrite the current Set in the Profile.
     * @param profileId related to where we're editing the set.
     * @return The updated set- and profileID.
     */
    @PatchMapping("/{profileId}/set")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileSet(@PathVariable Long profileId, @RequestBody Set set) {
        return profileService.updateSet(profileId, set);
    }

    @PatchMapping("/{profileId}/programs")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfilePrograms(@PathVariable Long profileId, @RequestBody List<Program> programs) {
        return profileService.updatePrograms(profileId, programs);
    }

    /**
     * Update an existing Workout in profile
     * @param workout to overwrite the current Workout in the Profile.
     * @param profileId related to where we're editing the workout.
     * @return The updated workout- and profileID.
     */
    @PatchMapping("/{profileId}/workout")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileWorkout(@PathVariable Long profileId, @RequestBody Workout workout) {
        return profileService.updateWorkout(profileId, workout);
    }

    @PatchMapping("/{profileId}/workouts")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileWorkouts(@PathVariable Long profileId, @RequestBody List<Workout> workouts) {
        return profileService.updateWorkouts(profileId, workouts);
    }

    @DeleteMapping("/{profileId}/workout/{workoutId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> deleteProfileWorkout(@PathVariable Long profileId, @PathVariable Long workoutId) {
        return profileService.removeWorkout(profileId, workoutId);
    }

    @DeleteMapping("/{profileId}/program/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> deleteProfileProgram(@PathVariable Long profileId, @PathVariable Long programId) {
        return profileService.removeProgram(profileId, programId);
    }
    // Update => User (is located in KeyCloak)
}
