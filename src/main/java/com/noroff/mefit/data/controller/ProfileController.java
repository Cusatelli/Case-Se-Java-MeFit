package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.service.ProfileService;
import com.noroff.mefit.data.model.Profile;
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
    public Profile updateProfile(@PathVariable Long profileId, @RequestBody Profile profile) {
        return profileService.update(profileId, profile);
    }

    @DeleteMapping("/{profileId}")
    public Boolean deleteProfile(@PathVariable Long profileId) {
        return profileService.delete(profileId);
    }
}
