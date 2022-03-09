package com.noroff.mefit.controller;

import com.noroff.mefit.model.User;
import com.noroff.mefit.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User")
@RequestMapping("/api/user")
public record UserController(UserService userService) {

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @PatchMapping("/{userId}")
    public User update(@RequestBody User user, @PathVariable Long userId) {
        return userService.update(user, userId);
    }

    @DeleteMapping("/{userId}")
    public Boolean delete(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}