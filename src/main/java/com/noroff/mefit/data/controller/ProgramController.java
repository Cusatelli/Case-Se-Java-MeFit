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
public record ProgramController(ProgramService programService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Program>>> getAllPrograms() {
        return programService.getAll();
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Program>> createProgram(@RequestBody Program program) {
        return programService.create(program);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<DefaultResponse<Program>> getProgramById(@PathVariable Long programId) {
        return programService.getById(programId);
    }

    @PatchMapping("/{programId}")
    public ResponseEntity<DefaultResponse<Program>> updateProgram(@PathVariable Long programId, @RequestBody Program program) {
        return programService.update(programId, program);
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<DefaultResponse<Void>> deleteProgram(@PathVariable Long programId) {
        return programService.delete(programId);
    }
}
