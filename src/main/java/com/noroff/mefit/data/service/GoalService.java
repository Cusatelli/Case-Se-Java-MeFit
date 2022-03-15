package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Goal;
import com.noroff.mefit.data.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record GoalService(GoalRepository goalRepository) {
    public List<Goal> getAll() {
        return goalRepository.findAll();
    }

    public Goal create(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal getById(Long goalId) {
        if (!goalRepository.existsById(goalId)) { return null; }
        return goalRepository.findById(goalId).orElse(null);
    }

    public Goal update(Long goalId, Goal goal) {
        if (!goalRepository.existsById(goalId)) { return null; }

        Goal prevGoal = goalRepository.findById(goalId).orElse(null);
        if(prevGoal == null) { return null; }

        goal.setId(prevGoal.getId());
        return goalRepository.save(goal);
    }

    public Boolean delete(Long goalId) {
        if (!goalRepository.existsById(goalId)) { return false; }

        goalRepository.deleteById(goalId);
        return !goalRepository.existsById(goalId);
    }
}