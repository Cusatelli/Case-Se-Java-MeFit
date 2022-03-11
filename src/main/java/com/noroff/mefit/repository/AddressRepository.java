package com.noroff.mefit.repository;

import com.noroff.mefit.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
