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

    /**
     * Get all addresses through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of addresses.
     */
    public ResponseEntity<DefaultResponse<List<Address>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultResponse<>(addressRepository.findAll())
        );
    }

    /**
     * Find a specific Address from its ID value through the exposed JPA Repository getById() method.
     * If address not found return correct response code,
     * if address has no content return correct response code
     * @param addressId The Long ID to search for in Address database.
     * @return The Address Model found by getById() method.
     */
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

    /**
     * Create a new Address through the exposed JPA Repository save method.
     * using reasonable responses
     * @param address Address Model.
     * @return The created Address Model.
     */
    public ResponseEntity<DefaultResponse<Address>> create(Address address) {
        return ResponseEntity.status(HttpStatus.CREATED).location(ConfigSettings.HTTP.location(TAG.toLowerCase())).body(
                new DefaultResponse<>(addressRepository.save(address))
        );
    }

    /**
     * Update an existing Address in database from its ID value, through the exposed JPA Repository save() method.
     * If address not found return correct response code,
     * if address has no content return correct response code
     * @param address New Address Model to overwrite the current Address in database.
     * @param addressId ID to overwrite in database.
     * @return The updated Address Model.
     */
    public ResponseEntity<DefaultResponse<Address>> update(Long addressId, Address address) {
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

    /**
     * Delete an address in database from ID input value, through exposed JPA Repository deleteById().
     * If address not found return correct response code,
     * if address has no content return correct response code
     * @param addressId Address ID to delete.
     * @return True if address does not exist anymore. (Successful delete).
     */
    public ResponseEntity<DefaultResponse<Address>> delete(Long addressId) {
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
