package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Profile;
import com.noroff.mefit.data.model.User;
import com.noroff.mefit.data.repository.ProfileRepository;
import com.noroff.mefit.data.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(
        UserRepository userRepository,
        ProfileRepository profileRepository
) {
    private static final String TAG = User.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<User>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(userRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<User>> getById(Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, userId))
            );
        }

        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(user)
        );
    }

    public ResponseEntity<DefaultResponse<User>> create(User user) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(userRepository.save(user))
        );
    }

    public ResponseEntity<DefaultResponse<User>> update(Long userId, User user) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, userId))
            );
        }

        User dbUser = userRepository.findById(userId).orElse(null);
        if(dbUser == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        user.setId(dbUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(userRepository.save(user))
        );
    }

    public ResponseEntity<DefaultResponse<User>> linkUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, userId))
            );
        }
        user.setId(userId);

        Profile profile = new Profile();
        profile.setUser(user);
        Profile savedProfile = profileRepository.save(profile);
        if(!profileRepository.existsById(savedProfile.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND("Profile", userId))
            );
        }

        user.setProfile(savedProfile);
        User savedUser = userRepository.save(user);
        if(!userRepository.existsById(savedUser.getId())) {
            profileRepository.deleteById(savedProfile.getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(savedUser)
        );
    }

    public ResponseEntity<DefaultResponse<Void>> delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, userId))
            );
        }

        userRepository.deleteById(userId);

        if(userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, userId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
