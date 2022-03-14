package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.User;
import com.noroff.mefit.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(UserRepository userRepository) {
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getById(Long userId) {
        if (!userRepository.existsById((userId))) {
            return null;
        }

        return userRepository.findById(userId).orElse(null);
    }

    public User update(Long userId, User user) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        user.setId(userId);
        return userRepository.save(user);
    }

    public Boolean delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            return false;
        }

        userRepository.deleteById(userId);
        return !userRepository.existsById(userId);
    }
}
