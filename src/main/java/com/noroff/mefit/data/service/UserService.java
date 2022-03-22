package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Profile;
import com.noroff.mefit.data.model.User;
import com.noroff.mefit.data.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(
        UserRepository userRepository,
        ProfileService profileService
) {
    private static final String TAG = User.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<User>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(userRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<User>> getById(Long userId) {
        if (!userRepository.existsById(userId)) { return RESPONSE_NOT_FOUND(userId); }

        User user = userRepository.findById(userId).orElse(null);
        if(user == null) { return RESPONSE_NO_CONTENT(); }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(user)
        );
    }

    @Deprecated
    public ResponseEntity<DefaultResponse<User>> deprecated_create(User user) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(userRepository.save(user))
        );
    }

    public ResponseEntity<DefaultResponse<User>> create(User user) {
        User savedUser = userRepository.save(user);
        if(!userRepository.existsById(savedUser.getId())) { return RESPONSE_NOT_FOUND(user.getId()); }

        return linkUserProfile(savedUser);
    }

    public ResponseEntity<DefaultResponse<User>> linkUserProfile(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        DefaultResponse<Profile> response = profileService.create(profile).getBody();

        if(response == null) { return RESPONSE_BAD_REQUEST(); }
        if(!response.getSuccess()) {
            profileService.delete(response.getPayload().getId());
            return RESPONSE_BAD_REQUEST();
        }

        // TODO: Delete profile if user does not exists or on error

        user.setProfile(response.getPayload());
        User savedUser = userRepository.save(user);
        if(!userRepository.existsById(savedUser.getId())) {
            profileService.delete(response.getPayload().getId());
        }

        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(savedUser)
        );
    }

    public ResponseEntity<DefaultResponse<User>> linkUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return linkUserProfile(user);
    }

    public ResponseEntity<DefaultResponse<User>> update(Long userId, User user) {
        if (!userRepository.existsById(userId)) { return RESPONSE_NOT_FOUND(userId); }

        User dbUser = userRepository.findById(userId).orElse(null);
        if(dbUser == null) { return RESPONSE_NO_CONTENT(); }

        user.setId(dbUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(userRepository.save(user))
        );
    }

    public ResponseEntity<DefaultResponse<User>> delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            return RESPONSE_NOT_FOUND(userId);
        }

        userRepository.deleteById(userId);

        if(userRepository.existsById(userId)) {
            return RESPONSE_FOUND(userId);
        }

        return RESPONSE_NO_CONTENT();
    }

    private static ResponseEntity<DefaultResponse<User>> RESPONSE_NO_CONTENT() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }

    private static ResponseEntity<DefaultResponse<User>> RESPONSE_FOUND(Long userId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, userId))
        );
    }

    private static ResponseEntity<DefaultResponse<User>> RESPONSE_NOT_FOUND(Long userId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, userId))
        );
    }

    private static ResponseEntity<DefaultResponse<User>> RESPONSE_BAD_REQUEST() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), DefaultResponse.BAD_REQUEST(TAG))
        );
    }
}
