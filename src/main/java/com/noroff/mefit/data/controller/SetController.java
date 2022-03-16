package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.service.SetService;
import com.noroff.mefit.data.model.Set;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Set")
@RequestMapping("/api/set")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin("${server.cors.application_origin}")
public record SetController(SetService setService) {
    @GetMapping
    public List<Set> getAllSets() {
        return setService.getAll();
    }

    @PostMapping
    public Set createSet(@RequestBody Set set) {
        return setService.create(set);
    }

    @GetMapping("/{setId}")
    public Set getSetById(@PathVariable Long setId) {
        return setService.getById(setId);
    }

    @PatchMapping("/{setId}")
    public Set updateSet(@PathVariable Long setId, @RequestBody Set set) {
        return setService.update(setId, set);
    }

    @DeleteMapping("/{setId}")
    public Boolean deleteSet(@PathVariable Long setId) {
        return setService.delete(setId);
    }

}
