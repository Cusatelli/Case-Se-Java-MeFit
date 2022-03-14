package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.service.ProgramService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Program")
@RequestMapping("/api/program")
public record ProgramController(ProgramService programService) {
    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAll();
    }

    @PostMapping
    public Program createProgram(@RequestBody Program program) {
        return programService.create(program);
    }

    @GetMapping("/{programId}")
    public Program getProgramById(@PathVariable Long programId) {
        return programService.getById(programId);
    }

    @PatchMapping("/{programId}")
    public Program updateProgram(@PathVariable Long programId, @RequestBody Program program) {
        return programService.update(programId, program);
    }

    @DeleteMapping("/{programId}")
    public Boolean deleteProgram(@PathVariable Long programId) {
        return programService.delete(programId);
    }
}
