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

    /**
     * Get all programs through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of programs.
     */
    public ResponseEntity<DefaultResponse<List<Program>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(programRepository.findAll())
        );
    }

    /**
     * Find a specific Program from its ID value through the exposed JPA Repository getById() method.
     * If program not found return correct response code,
     * if program has no content return correct response code
     * @param programId The Long ID to search for in Program database.
     * @return The Program Model found by getById() method.
     */
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
    /**
     * Create a new Program through the exposed JPA Repository save method.
     * using reasonable responses
     * @param program Program Model.
     * @return The created Program Model.
     */
    public ResponseEntity<DefaultResponse<Program>> create(Program program) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(programRepository.save(program))
        );
    }

    /**
     * Update an existing Program in database from its ID value, through the exposed JPA Repository save() method.
     * If program not found return correct response code,
     * if program has no content return correct response code
     * @param program New Program Model to overwrite the current Program in database.
     * @param programId ID to overwrite in database.
     * @return The updated Program Model.
     */
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
    /**
     * Delete a program in database from ID input value, through exposed JPA Repository deleteById().
     * If program not found return correct response code,
     * if program has no content return correct response code
     * @param programId Program ID to delete.
     * @return True if program does not exist anymore. (Successful delete).
     */
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
