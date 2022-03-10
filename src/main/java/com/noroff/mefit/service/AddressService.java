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

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address getById(Long setId) {
        if (!addressRepository.existsById(setId)) {
            return null;
        }

        return addressRepository.findById(setId).orElse(null);
    }

    public Address update(Address address, Long setId) {
        if (!addressRepository.existsById(setId)) {
            return null;
        }

        Address prevAddress = addressRepository.findById(setId).orElse(null);
        if(prevAddress == null) {
            return null;
        }

        address.setId(prevAddress.getId());
        return addressRepository.save(address);
    }

    public Boolean delete(Long setId) {
        if (!addressRepository.existsById(setId)) {
            return false;
        }

        addressRepository.deleteById(setId);
        return !addressRepository.existsById(setId);
    }
}
