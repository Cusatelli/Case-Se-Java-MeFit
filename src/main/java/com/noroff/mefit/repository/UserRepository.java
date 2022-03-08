package com.noroff.mefit.repository;

import com.noroff.mefit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
