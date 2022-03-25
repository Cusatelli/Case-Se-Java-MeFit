package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.SetService;
import com.noroff.mefit.data.model.Set;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Set")
@RequestMapping("/api/set")
// Service implementing Repository extending JPARepository
public record SetController(SetService setService) {

    /**
     * Get all sets through the exposed JPA Repository findAll method.
     * @return List of sets.
     */
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Set>>> getAllSets() {
        return setService.getAll();
    }

    /**
     * Create a new Set through the exposed JPA Repository save method.
     * @param set Set Model.
     * @return The created Set Model.
     */
    @PostMapping
    public ResponseEntity<DefaultResponse<Set>> createSet(@RequestBody Set set) {
        return setService.create(set);
    }

    /**
     * Find a specific Set from its ID value through the exposed JPA Repository getById() method.
     * @param setId The Long ID to search for in Set database.
     * @return The Set Model found by getById() method.
     */
    @GetMapping("/{setId}")
    public ResponseEntity<DefaultResponse<Set>> getSetById(@PathVariable Long setId) {
        return setService.getById(setId);
    }

    /**
     * Update an existing Set in database from its ID value, through the exposed JPA Repository save() method.
     * @param set New Set Model to overwrite the current Set in database.
     * @param setId ID to overwrite in database.
     * @return The updated Set Model.
     */
    @PatchMapping("/{setId}")
    public ResponseEntity<DefaultResponse<Set>> updateSet(@PathVariable Long setId, @RequestBody Set set) {
        return setService.update(setId, set);
    }

    /**
     * Delete a set in database from ID input value, through exposed JPA Repository deleteById().
     * @param setId Set ID to delete.
     * @return True if set does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{setId}")
    public ResponseEntity<DefaultResponse<Void>> deleteSet(@PathVariable Long setId) {
        return setService.delete(setId);
    }

}
