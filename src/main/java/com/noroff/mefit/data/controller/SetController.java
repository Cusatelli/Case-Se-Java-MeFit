package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.SetService;
import com.noroff.mefit.data.model.Set;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Set")
@AllArgsConstructor
@RequestMapping("/api/set")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin("${server.cors.application_origin}")
public class SetController {
    private final SetService setService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Set>>> getAllSets() {
        return setService.getAll();
    }

    @GetMapping("/{setId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Set>> getSetById(@PathVariable Long setId) {
        return setService.getById(setId);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Set>> createSet(@RequestBody Set set) {
        return setService.create(set);
    }

    @PatchMapping("/{setId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Set>> updateSet(@PathVariable Long setId, @RequestBody Set set) {
        return setService.update(setId, set);
    }

    @DeleteMapping("/{setId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteSet(@PathVariable Long setId) {
        return setService.delete(setId);
    }

}
