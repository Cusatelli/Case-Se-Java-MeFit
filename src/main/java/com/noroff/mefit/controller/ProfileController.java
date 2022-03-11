package com.noroff.mefit.controller;

import com.noroff.mefit.model.Profile;
import com.noroff.mefit.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Profile")
@RequestMapping("/api/profile")
public record ProfileController(ProfileService profileService) {

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAll();
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileService.create(profile);
    }

    @GetMapping("/{profileId}")
    public Profile getProfileById(@PathVariable Long profileId) {
        return profileService.getById(profileId);
    }

    @PatchMapping("/{profileId}")
    public Profile updateProfile(@RequestBody Profile profile, @PathVariable Long profileId) {
        return profileService.update(profile, profileId);
    }

    @DeleteMapping("/{profileId}")
    public Boolean deleteProfile(@PathVariable Long profileId) {
        return profileService.delete(profileId);
    }
}
