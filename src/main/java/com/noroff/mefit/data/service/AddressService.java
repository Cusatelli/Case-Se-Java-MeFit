package com.noroff.mefit.data.service;

import com.noroff.mefit.data.model.Address;
import com.noroff.mefit.data.repository.AddressRepository;
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

    public Address getById(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return null;
        }

        return addressRepository.findById(addressId).orElse(null);
    }

    public Address update(Address address, Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return null;
        }

        Address prevAddress = addressRepository.findById(addressId).orElse(null);
        if(prevAddress == null) {
            return null;
        }

        address.setId(prevAddress.getId());
        return addressRepository.save(address);
    }

    public Boolean delete(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return false;
        }

        addressRepository.deleteById(addressId);
        return !addressRepository.existsById(addressId);
    }
}
