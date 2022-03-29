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

    /**
     * Get all users through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of users.
     */
    public ResponseEntity<DefaultResponse<List<User>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(userRepository.findAll())
        );
    }

    /**
     * Find a specific User from its ID value through the exposed JPA Repository getById() method.
     * If user not found return correct response code,
     * if user has no content return correct response code
     * @param userId The Long ID to search for in User database.
     * @return The User Model found by getById() method.
     */
    public ResponseEntity<DefaultResponse<User>> getById(Integer userId) {
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

    /**
     * Create a new User through the exposed JPA Repository save method.
     * using reasonable responses
     * @param user User Model.
     * @return The created User Model.
     */
    public ResponseEntity<DefaultResponse<User>> create(User user) {
        User savedUser = userRepository.save(user);
        if(!userRepository.existsById(savedUser.getId())) { return RESPONSE_NOT_FOUND(user.getId()); }

        return linkUserProfile(savedUser);
    }

    // TODO: Error handling
    public ResponseEntity<DefaultResponse<User>> linkUserProfile(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        DefaultResponse<Profile> response = profileService.create(profile).getBody();
        if (response == null) { return RESPONSE_BAD_REQUEST(); }
        if (!response.getSuccess()) { return RESPONSE_NOT_FOUND("Profile", profile.getId()); }

        response = profileService.getById(response.getPayload().getId()).getBody();
        if (response == null || !response.getSuccess()) { return RESPONSE_NOT_FOUND("Profile", profile.getId()); }

        Profile savedProfile = response.getPayload();
        if(savedProfile == null) { return RESPONSE_BAD_REQUEST(); }

        savedProfile.setUser(user);
        response = profileService.update(savedProfile.getId(), savedProfile).getBody();
        if (response == null) { return RESPONSE_BAD_REQUEST(); }
        if (!response.getSuccess()) { return RESPONSE_NOT_FOUND("Profile", profile.getId()); }

        user.setProfile(savedProfile);
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(userRepository.save(user))
        );
    }

    /**
     * Update an existing User in database from its ID value, through the exposed JPA Repository save() method.
     * If user not found return correct response code,
     * if user has no content return correct response code
     * @param user New User Model to overwrite the current User in database.
     * @param userId ID to overwrite in database.
     * @return The updated User Model.
     */
    public ResponseEntity<DefaultResponse<User>> update(Integer userId, User user) {
        if (!userRepository.existsById(userId)) { return RESPONSE_NOT_FOUND(userId); }

        User dbUser = userRepository.findById(userId).orElse(null);
        if(dbUser == null) { return RESPONSE_NO_CONTENT(); }

        user.setId(dbUser.getId());
        user.setProfile(dbUser.getProfile());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(userRepository.save(user))
        );
    }

    /**
     * Delete a User in database from ID input value, through exposed JPA Repository deleteById().
     * If user not found return correct response code,
     * if user has no content return correct response code
     * @param userId Address ID to delete.
     * @return True if user does not exist anymore. (Successful delete).
     */
    public ResponseEntity<DefaultResponse<User>> delete(Integer userId) {
        if (!userRepository.existsById(userId)) {
            return RESPONSE_NOT_FOUND(userId);
        }

        userRepository.deleteById(userId);

        if(userRepository.existsById(userId)) {
            return RESPONSE_FOUND(userId);
        }

        return RESPONSE_NO_CONTENT();
    }

    /**
     * Delete all Users in database and delete also from profile.
     * If user not found return correct response code,
     * if user has no content return correct response code
     * @param userId Address ID to delete.
     * @return response no content if no user exist, (Successful delete).
     */
    public ResponseEntity<DefaultResponse<User>> deleteAll(Integer userId) {
        if (!userRepository.existsById(userId)) { return RESPONSE_NOT_FOUND(userId); }

        User user = userRepository.findById(userId).orElse(null);
        if(user == null) { return RESPONSE_NOT_FOUND(userId); }

        userRepository.deleteById(userId);
        if(userRepository.existsById(userId)) { return RESPONSE_FOUND(userId); }

        DefaultResponse<Profile> response = profileService.deleteAll(user.getProfile()).getBody();
        if(response == null) { return RESPONSE_BAD_REQUEST(); }
        if(response.getError().getStatus() != HttpStatus.NO_CONTENT.value()) { return RESPONSE_BAD_REQUEST(); }

        return RESPONSE_NO_CONTENT();
    }

    /**
     * Response message for user and status code
     * @return Human-readable no content message.
     */
    private static ResponseEntity<DefaultResponse<User>> RESPONSE_NO_CONTENT() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }

    /**
     * Response message for user and status code
     * @param userId User ID to find
     * @return Human-readable found user message.
     */
    private static ResponseEntity<DefaultResponse<User>> RESPONSE_FOUND(Integer userId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, userId))
        );
    }

    /**
     * Response message for user and status code
     * @param userId User ID to find
     * @return Human-readable not found message.
     */
    private static ResponseEntity<DefaultResponse<User>> RESPONSE_NOT_FOUND(Integer userId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, userId))
        );
    }

    /**
     * Response message for user and status code
     * @param name user
     * @param id userId
     * @return Human-readable not found message.
     */
    private static ResponseEntity<DefaultResponse<User>> RESPONSE_NOT_FOUND(String name, Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), "Could not find " + name + " with ID: " + id)
        );
    }

    /**
     * Response message for user and status code
     * @return Human-readable bad request (status code 400) message.
     */
    private static ResponseEntity<DefaultResponse<User>> RESPONSE_BAD_REQUEST() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DefaultResponse<>(HttpStatus.BAD_REQUEST.value(), DefaultResponse.BAD_REQUEST(TAG))
        );
    }
}
