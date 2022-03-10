package com.noroff.mefit.controller;

import com.noroff.mefit.model.Set;
import com.noroff.mefit.service.SetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Set")
@RequestMapping("/api/set")
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
    public Set updateSet(@RequestBody Set set, @PathVariable Long setId) {
        return setService.update(set, setId);
    }

    @DeleteMapping("/{setId}")
    public Boolean deleteSet(@PathVariable Long setId) {
        return setService.delete(setId);
    }

}
