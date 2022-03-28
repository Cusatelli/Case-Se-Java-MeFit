package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.UserService;
import com.noroff.mefit.data.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "User")
@RequestMapping("/api/user")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD, RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<User>>> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<User>> getUserById(@PathVariable String userId) {
        return userService.getById(userId.hashCode());
    }

    @PostMapping("/{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<User>> createUser(@PathVariable String userId, @RequestBody User user) {
        user.setId(userId.hashCode());
        return userService.create(user);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<User>> updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.update(userId.hashCode(), user);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<User>> deleteUser(@PathVariable String userId) {
        return userService.deleteAll(userId.hashCode());
    }
}
