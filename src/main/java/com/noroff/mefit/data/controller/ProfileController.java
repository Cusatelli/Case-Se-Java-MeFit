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
@CrossOrigin("${server.cors.application_origin}")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Profile>>> getAllProfiles() {
        return profileService.getAll();
    }

    @GetMapping("/{profileId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> getProfileById(@PathVariable Long profileId) {
        return profileService.getById(profileId);
    }

    @PatchMapping("/{profileId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfile(@PathVariable Long profileId, @RequestBody Profile profile) {
        return profileService.update(profileId, profile);
    }

    @PatchMapping("/{profileId}/address")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileAddress(@PathVariable Long profileId, @RequestBody Address address) {
        return profileService.updateAddress(profileId, address);
    }

    @PatchMapping("/{profileId}/goal")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileGoal(@PathVariable Long profileId, @RequestBody Goal goal) {
        return profileService.updateGoal(profileId, goal);
    }

    @PatchMapping("/{profileId}/program")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileProgram(@PathVariable Long profileId, @RequestBody Program program) {
        return profileService.updateProgram(profileId, program);
    }

    @PatchMapping("/{profileId}/set")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileSet(@PathVariable Long profileId, @RequestBody Set set) {
        return profileService.updateSet(profileId, set);
    }

    @PatchMapping("/{profileId}/workout")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Profile>> updateProfileSet(@PathVariable Long profileId, @RequestBody Workout workout) {
        return profileService.updateWorkout(profileId, workout);
    }

    // Update => User (is located in KeyCloak)
}
