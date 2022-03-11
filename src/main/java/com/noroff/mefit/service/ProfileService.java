package com.noroff.mefit.service;

import com.noroff.mefit.model.Profile;
import com.noroff.mefit.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProfileService(ProfileRepository profileRepository) {
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getById(Long profileId) {
        if (!profileRepository.existsById((profileId))) {
            return null;
        }

        return profileRepository.findById(profileId).orElse(null);
    }

    public Profile update(Profile profile, Long profileId) {
        if (!profileRepository.existsById((profileId))) {
            return null;
        }

        Profile prevProfile = profileRepository.findById(profileId).orElse(null);
        if(prevProfile == null) {
            return null;
        }

        profile.setId(prevProfile.getId());
        return profileRepository.save(profile);
    }

    public Boolean delete(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            return false;
        }

        profileRepository.deleteById(profileId);
        return !profileRepository.existsById(profileId);
    }
}
