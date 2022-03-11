package com.noroff.mefit.controller;

import com.noroff.mefit.model.Address;
import com.noroff.mefit.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Address")
@RequestMapping("/api/address")
public record AddressController(AddressService addressService) {
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAll();
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    @GetMapping("/{addressId}")
    public Address getAddressById(@PathVariable Long addressId) {
        return addressService.getById(addressId);
    }

    @PatchMapping("/{addressId}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Long addressId) {
        return addressService.update(address, addressId);
    }

    @DeleteMapping("/{addressId}")
    public Boolean deleteAddress(@PathVariable Long addressId) {
        return addressService.delete(addressId);
    }
}
