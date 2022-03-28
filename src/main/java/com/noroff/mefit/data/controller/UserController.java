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
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD,
                RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public record UserController(UserService userService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<User>>> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> getUserById(@PathVariable String userId) {
        return userService.getById(userId.hashCode());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> createUser(@PathVariable String userId, @RequestBody User user) {
        user.setId(userId.hashCode());
        return userService.create(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.update(userId.hashCode(), user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DefaultResponse<User>> deleteUser(@PathVariable String userId) {
        return userService.deleteAll(userId.hashCode());
    }
}
