package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> { }
