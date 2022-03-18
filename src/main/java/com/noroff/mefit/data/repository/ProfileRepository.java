package com.noroff.mefit.data.repository;

import com.noroff.mefit.data.model.Profile;
import com.noroff.mefit.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findProfileByUser(User user);

}