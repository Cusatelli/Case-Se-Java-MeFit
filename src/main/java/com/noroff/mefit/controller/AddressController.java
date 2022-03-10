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
    public Address createAddress(@RequestBody Address set) {
        return addressService.create(set);
    }

    @GetMapping("/{setId}")
    public Address getAddressById(@PathVariable Long setId) {
        return addressService.getById(setId);
    }

    @PatchMapping("/{setId}")
    public Address updateAddress(@RequestBody Address set, @PathVariable Long setId) {
        return addressService.update(set, setId);
    }

    @DeleteMapping("/{setId}")
    public Boolean deleteAddress(@PathVariable Long setId) {
        return addressService.delete(setId);
    }
}
