package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Address with JpaRepository.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> { }
