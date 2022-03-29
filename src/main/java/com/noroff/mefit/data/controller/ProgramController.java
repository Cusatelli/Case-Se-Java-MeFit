package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.service.ProgramService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Program")
@RequestMapping("/api/program")
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
public class ProgramController {
    private final ProgramService programService;

    /**
     * Get all programs through the exposed JPA Repository findAll method.
     * @return List of characters.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Program>>> getAllPrograms() {
        return programService.getAll();
    }

    /**
     * Find a specific Program from its ID value through the exposed JPA Repository getById() method.
     * @param programId The Long ID to search for in Program database.
     * @return The Program Model found by getById() method.
     */
    @GetMapping("/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Program>> getProgramById(@PathVariable Long programId) {
        return programService.getById(programId);
    }

    /**
     * Create a new Program through the exposed JPA Repository save method.
     * @param program Program Model.
     * @return The created Program Model.
     */
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Program>> createProgram(@RequestBody Program program) {
        return programService.create(program);
    }

    /**
     * Update an existing PRogram in database from its ID value, through the exposed JPA Repository save() method.
     * @param program New Program Model to overwrite the current Program in database.
     * @param programId ID to overwrite in database.
     * @return The updated Program Model.
     */
    @PatchMapping("/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Program>> updateProgram(@PathVariable Long programId, @RequestBody Program program) {
        return programService.update(programId, program);
    }

    /**
     * Delete a program in database from ID input value, through exposed JPA Repository deleteById().
     * @param programId Program ID to delete.
     * @return True if program does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteProgram(@PathVariable Long programId) {
        return programService.delete(programId);
    }
}
