package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Program;
import com.noroff.mefit.data.model.ProgramWorkout;
import com.noroff.mefit.data.repository.ProgramWorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProgramWorkoutService(ProgramWorkoutRepository programWorkoutRepository) {
    public List<ProgramWorkout> getAll() {
        return programWorkoutRepository.findAll();
    }

    public ProgramWorkout create(ProgramWorkout programWorkout) {
        return programWorkoutRepository.save(programWorkout);
    }

    public ProgramWorkout getById(Long programWorkoutId) {
        if (!programWorkoutRepository.existsById((programWorkoutId))) {
            return null;
        }

        return programWorkoutRepository.findById(programWorkoutId).orElse(null);
    }

    public ProgramWorkout update(Long programWorkoutId, ProgramWorkout programWorkout) {
        if (!programWorkoutRepository.existsById(programWorkoutId)) {
            return null;
        }

        ProgramWorkout prevProgramWorkout = programWorkoutRepository.findById(programWorkoutId).orElse(null);
        if(prevProgramWorkout == null) {
            return null;
        }

        programWorkout.setId(prevProgramWorkout.getId());
        return programWorkoutRepository.save(programWorkout);
    }

    public Boolean delete(Long programWorkoutId) {
        if (!programWorkoutRepository.existsById(programWorkoutId)) {
            return false;
        }

        programWorkoutRepository.deleteById(programWorkoutId);
        return !programWorkoutRepository.existsById(programWorkoutId);
    }
}
