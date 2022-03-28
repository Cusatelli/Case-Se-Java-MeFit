package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.Address;
import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.AddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Address")
@RequestMapping("/api/address")
@SecurityRequirement(name = "keycloak_implicit")
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD, RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    @PreAuthorize("permitAll()") // Let all through
    public ResponseEntity<DefaultResponse<List<Address>>> getAllAddresses() {
        return addressService.getAll();
    }

    @GetMapping("/{addressId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> getAddressById(@PathVariable Long addressId) {
        return addressService.getById(addressId);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    @PatchMapping("/{addressId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        return addressService.update(addressId, address);
    }

    @DeleteMapping("/{addressId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> deleteAddress(@PathVariable Long addressId) {
        return addressService.delete(addressId);
    }
}
