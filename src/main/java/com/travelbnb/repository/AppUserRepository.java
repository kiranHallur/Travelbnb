package com.travelbnb.repository;

import com.travelbnb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByEmail(String Email);
    boolean existsByUsername(String username);
     Optional<AppUser> findByUsername(String username);
}