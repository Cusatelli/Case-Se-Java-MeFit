package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.*;
import com.noroff.mefit.data.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Profile")
@RequestMapping("/api/profile")
// Service implementing Repository extending JPARepository
public record ProfileController(ProfileService profileService) {

    /**
     * Get everything in profile through the exposed JPA Repository findAll method.
     * @return List of profiles.
     */
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Profile>>> getAllProfiles() {
        return profileService.getAll();
    }

    /**
     * Find a specific PRofile from its ID value through the exposed JPA Repository getById() method.
     * @param profileId The Long ID to search for in Profile database.
     * @return The Profile Model found by getById() method.
     */
    @GetMapping("/{profileId}")
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
    public ResponseEntity<DefaultResponse<Profile>> updateProfile(@PathVariable Long profileId, @RequestBody Profile profile) {
        return profileService.update(profileId, profile);
    }

    @PatchMapping("/{profileId}/address")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileAddress(@PathVariable Long profileId, @RequestBody Address address) {
        return profileService.updateAddress(profileId, address);
    }

    @PatchMapping("/{profileId}/goal")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileGoal(@PathVariable Long profileId, @RequestBody Goal goal) {
        return profileService.updateGoal(profileId, goal);
    }

    @PatchMapping("/{profileId}/program")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileProgram(@PathVariable Long profileId, @RequestBody Program program) {
        return profileService.updateProgram(profileId, program);
    }

    @PatchMapping("/{profileId}/set")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileSet(@PathVariable Long profileId, @RequestBody Set set) {
        return profileService.updateSet(profileId, set);
    }

    @PatchMapping("/{profileId}/workout")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileSet(@PathVariable Long profileId, @RequestBody Workout workout) {
        return profileService.updateWorkout(profileId, workout);
    }

    // Update => User (is located in KeyCloak)
}
