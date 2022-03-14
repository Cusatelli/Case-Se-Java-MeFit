package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProgramService(ProgramRepository programRepository) {
    public List<Program> getAll() {
        return programRepository.findAll();
    }

    public Program create(Program program) {
        return programRepository.save(program);
    }

    public Program getById(Long programId) {
        if (!programRepository.existsById((programId))) {
            return null;
        }

        return programRepository.findById(programId).orElse(null);
    }

    public Program update(Long programId, Program program) {
        if (!programRepository.existsById(programId)) {
            return null;
        }

        Program prevProgram = programRepository.findById(programId).orElse(null);
        if(prevProgram == null) {
            return null;
        }

        program.setId(prevProgram.getId());
        return programRepository.save(program);
    }

    public Boolean delete(Long programId) {
        if (!programRepository.existsById(programId)) {
            return false;
        }

        programRepository.deleteById(programId);
        return !programRepository.existsById(programId);
    }
}
