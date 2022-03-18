package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Profile;
import com.noroff.mefit.data.model.User;
import com.noroff.mefit.data.repository.ProfileRepository;
import com.noroff.mefit.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record UserService(UserRepository userRepository, ProfileRepository profileRepository) {
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        return profileRepository.save(profile).getUser();
    }

    public User getById(String userId) {
        if (!userRepository.existsById((userId))) {
            return null;
        }
        return userRepository.findById(userId).orElse(null);
    }


    public User update(User user, String userId) {
        if (!userRepository.existsById((userId))) {
            return null;
        }
        user.setId(userId);
        return userRepository.save(user);
    }

    public Boolean delete(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Optional<Profile> profileOptional = profileRepository.findProfileByUser(userOptional.get());
            if (profileOptional.isPresent()) {
                profileRepository.delete(profileOptional.get());
                return true;
            }
        }
        return false;
    }
}
