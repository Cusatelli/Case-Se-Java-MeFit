package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.service.ProgramService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Program")
@RequestMapping("/api/program")
// Service implementing Repository extending JPARepository
public record ProgramController(ProgramService programService) {

    /**
     * Get all programs through the exposed JPA Repository findAll method.
     * @return List of characters.
     */
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Program>>> getAllPrograms() {
        return programService.getAll();
    }

    /**
     * Create a new Program through the exposed JPA Repository save method.
     * @param program Program Model.
     * @return The created Program Model.
     */
    @PostMapping
    public ResponseEntity<DefaultResponse<Program>> createProgram(@RequestBody Program program) {
        return programService.create(program);
    }

    /**
     * Find a specific Program from its ID value through the exposed JPA Repository getById() method.
     * @param programId The Long ID to search for in Program database.
     * @return The Program Model found by getById() method.
     */
    @GetMapping("/{programId}")
    public ResponseEntity<DefaultResponse<Program>> getProgramById(@PathVariable Long programId) {
        return programService.getById(programId);
    }

    /**
     * Update an existing PRogram in database from its ID value, through the exposed JPA Repository save() method.
     * @param program New Program Model to overwrite the current Program in database.
     * @param programId ID to overwrite in database.
     * @return The updated Program Model.
     */
    @PatchMapping("/{programId}")
    public ResponseEntity<DefaultResponse<Program>> updateProgram(@PathVariable Long programId, @RequestBody Program program) {
        return programService.update(programId, program);
    }

    /**
     * Delete a program in database from ID input value, through exposed JPA Repository deleteById().
     * @param programId Program ID to delete.
     * @return True if program does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{programId}")
    public ResponseEntity<DefaultResponse<Void>> deleteProgram(@PathVariable Long programId) {
        return programService.delete(programId);
    }
}
