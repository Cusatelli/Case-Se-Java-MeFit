package com.noroff.mefit.service;

import com.noroff.mefit.model.Address;
import com.noroff.mefit.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AddressService(AddressRepository addressRepository) {
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address create(Address set) {
        return addressRepository.save(set);
    }

    public Address getById(Long setId) {
        if (!addressRepository.existsById(setId)) {
            return null;
        }

        return addressRepository.findById(setId).orElse(null);
    }

    public Address update(Address set, Long setId) {
        if (!addressRepository.existsById(setId)) {
            return null;
        }

        Address prevSet = addressRepository.findById(setId).orElse(null);
        if(prevSet == null) {
            return null;
        }

        set.setId(prevSet.getId());
        return addressRepository.save(set);
    }

    public Boolean delete(Long setId) {
        if (!addressRepository.existsById(setId)) {
            return false;
        }

        addressRepository.deleteById(setId);
        return !addressRepository.existsById(setId);
    }
}
