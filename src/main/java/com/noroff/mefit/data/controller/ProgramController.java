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

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<List<Program>>> getAllPrograms() {
        return programService.getAll();
    }

    @GetMapping("/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Program>> getProgramById(@PathVariable Long programId) {
        return programService.getById(programId);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Program>> createProgram(@RequestBody Program program) {
        return programService.create(program);
    }

    @PatchMapping("/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Program>> updateProgram(@PathVariable Long programId, @RequestBody Program program) {
        return programService.update(programId, program);
    }

    @DeleteMapping("/{programId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Void>> deleteProgram(@PathVariable Long programId) {
        return programService.delete(programId);
    }
}
