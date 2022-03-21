package com.noroff.mefit.data.service;

import com.noroff.mefit.config.ConfigSettings;
import com.noroff.mefit.data.model.Address;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AddressService(AddressRepository addressRepository) {
    private static final String TAG = Address.class.getSimpleName();

    public ResponseEntity<DefaultResponse<List<Address>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(addressRepository.findAll())
        );
    }

    public ResponseEntity<DefaultResponse<Address>> getById(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, addressId))
            );
        }

        Address address = addressRepository.findById(addressId).orElse(null);
        if(address == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(address)
        );
    }

    public ResponseEntity<DefaultResponse<Address>> create(Address address) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(addressRepository.save(address))
        );
    }

    public ResponseEntity<DefaultResponse<Address>> update(Address address, Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, addressId))
            );
        }

        Address dbAddress = addressRepository.findById(addressId).orElse(null);
        if(dbAddress == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
            );
        }

        address.setId(dbAddress.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(addressRepository.save(address))
        );
    }

    public ResponseEntity<DefaultResponse<Void>> delete(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DefaultResponse<>(HttpStatus.NOT_FOUND.value(), DefaultResponse.NOT_FOUND(TAG, addressId))
            );
        }

        addressRepository.deleteById(addressId);

        if(addressRepository.existsById(addressId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new DefaultResponse<>(HttpStatus.FOUND.value(), DefaultResponse.FOUND(TAG, addressId))
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DefaultResponse<>(HttpStatus.NO_CONTENT.value(), DefaultResponse.NO_CONTENT(TAG))
        );
    }
}
