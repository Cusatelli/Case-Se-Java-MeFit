package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.repository.ProgramRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProgramService(ProgramRepository programRepository) {
    private static final String TAG = Program.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<Program>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(programRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<Program>> getById(Long programId) {
        if (!programRepository.existsById(programId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, programId))
            );
        }

        Program program = programRepository.findById(programId).orElse(null);
        if(program == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(program)
        );
    }

    public ResponseEntity<DefaultResponse<Program>> create(Program program) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(programRepository.save(program))
        );
    }

    public ResponseEntity<DefaultResponse<Program>> update(Long programId, Program program) {
        if (!programRepository.existsById(programId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, programId))
            );
        }

        Program dbProgram = programRepository.findById(programId).orElse(null);
        if(dbProgram == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        program.setId(dbProgram.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(programRepository.save(program))
        );
    }

    public ResponseEntity<DefaultResponse<Void>> delete(Long programId) {
        if (!programRepository.existsById(programId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, programId))
            );
        }

        programRepository.deleteById(programId);

        if(programRepository.existsById(programId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, programId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
