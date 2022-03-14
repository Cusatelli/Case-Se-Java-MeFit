package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Set;
import com.noroff.mefit.data.repository.SetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record SetService(SetRepository setRepository) {
    public List<Set> getAll() {
        return setRepository.findAll();
    }

    public Set create(Set set) {
        return setRepository.save(set);
    }

    public Set getById(Long setId) {
        if (!setRepository.existsById(setId)) {
            return null;
        }

        return setRepository.findById(setId).orElse(null);
    }

    public Set update(Long setId, Set set) {
        if (!setRepository.existsById(setId)) {
            return null;
        }

        Set prevSet = setRepository.findById(setId).orElse(null);
        if(prevSet == null) {
            return null;
        }

        set.setId(prevSet.getId());
        set.setExercise(prevSet.getExercise());
        return setRepository.save(set);
    }

    public Boolean delete(Long setId) {
        if (!setRepository.existsById(setId)) {
            return false;
        }

        setRepository.deleteById(setId);
        return !setRepository.existsById(setId);
    }
}
