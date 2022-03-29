package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * use: full CRUD in Profile with JpaRepository.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> { }
