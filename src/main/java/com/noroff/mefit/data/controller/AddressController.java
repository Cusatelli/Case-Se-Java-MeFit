package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.Address;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Address")
@RequestMapping("/api/address")
public record AddressController(AddressService addressService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Address>>> getAllAddresses() {
        return addressService.getAll();
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Address>> createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<DefaultResponse<Address>> getAddressById(@PathVariable Long addressId) {
        return addressService.getById(addressId);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<DefaultResponse<Address>> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        return addressService.update(addressId, address);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<DefaultResponse<Address>> deleteAddress(@PathVariable Long addressId) {
        return addressService.delete(addressId);
    }
}
