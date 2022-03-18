package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.service.UserService;
import com.noroff.mefit.data.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User")
@RequestMapping("/api/user")
public record UserController(UserService userService) {
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getById(userId);
    }

    @PatchMapping("/{userId}")

    public User updateUser(@RequestBody User user, @PathVariable String userId) {
        return userService.update(user, userId);
    }

    @DeleteMapping("/{userId}")
    public Boolean deleteUser(@PathVariable String userId) {
        return userService.delete(userId);
    }
}
