package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.UserService;
import com.noroff.mefit.data.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User")
@RequestMapping("/api/user")
public record UserController(UserService userService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<User>>> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> getUserById(@PathVariable String userId) {
        return userService.getById(userId);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<User>> createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.update(userId, user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> deleteUser(@PathVariable String userId) {
        return userService.deleteAll(userId);
    }
}
