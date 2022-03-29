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


    /**
     * Get all addresses through the exposed JPA Repository findAll method.
     * using reasonable responses
     * @return List of addresses.
     */
    @GetMapping
    @PreAuthorize("permitAll()") // Let all through
    public ResponseEntity<DefaultResponse<List<Address>>> getAllAddresses() {
        return addressService.getAll();
    }

    /**
     * Create a new Address through the exposed JPA Repository save method.
     * using reasonable responses
     * @param address Address Model.
     * @return The created Character Model.
     */
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    /**
     * Find a specific Address from its ID value through the exposed JPA Repository getById() method.
     * @param addressId The Long ID to search for in Address database.
     * @return The Address Model found by getById() method.
     */
    @GetMapping("/{addressId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> getAddressById(@PathVariable Long addressId) {
        return addressService.getById(addressId);
    }

    /**
     * Update an existing Address in database from its ID value, through the exposed JPA Repository save() method.
     * @param address New Address Model to overwrite the current Address in database.
     * @param addressId ID to overwrite in database.
     * @return The updated Address Model.
     */
    @PatchMapping("/{addressId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        return addressService.update(addressId, address);
    }

    /**
     * Delete a address in database from ID input value, through exposed JPA Repository deleteById().
     * @param addressId Address ID to delete.
     * @return True if address does not exist anymore. (Successful delete).
     */
    @DeleteMapping("/{addressId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<DefaultResponse<Address>> deleteAddress(@PathVariable Long addressId) {
        return addressService.delete(addressId);
    }
}
