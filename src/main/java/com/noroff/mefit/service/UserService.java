package com.noroff.mefit.service;

import com.noroff.mefit.model.User;
import com.noroff.mefit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(UserRepository userRepository) {

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getById(Long userId) {
        if (!userRepository.existsById((userId))) {
            return null;
        }

        return userRepository.findById(userId).get();
    }

    public User updateUser(User user, Long userId) {
        if (!userRepository.existsById((userId))) {
            return null;
        }

        User updatedUser = userRepository.getById(userId);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setContributor(user.getContributor());
        updatedUser.setAdmin(user.getAdmin());

        return userRepository.save(updatedUser);
    }

    public Boolean deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            return false;
        }

        userRepository.deleteById(userId);

        return !userRepository.existsById(userId);
    }
}