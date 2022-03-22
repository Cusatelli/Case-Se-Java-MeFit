package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.ProfileService;
import com.noroff.mefit.data.model.Profile;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Profile")
@RequestMapping("/api/profile")
public record ProfileController(ProfileService profileService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Profile>>> getAllProfiles() {
        return profileService.getAll();
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<DefaultResponse<Profile>> getProfileById(@PathVariable Long profileId) {
        return profileService.getById(profileId);
    }

    @PatchMapping("/{profileId}")
    public ResponseEntity<DefaultResponse<Profile>> updateProfile(@PathVariable Long profileId, @RequestBody Profile profile) {
        return profileService.update(profileId, profile);
    }
}
