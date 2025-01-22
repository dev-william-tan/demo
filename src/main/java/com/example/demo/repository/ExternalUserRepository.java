package com.example.demo.repository;

import com.example.demo.model.ExternalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalUserRepository extends JpaRepository<ExternalUser, Long> {
    Optional<ExternalUser> findByUsername(String username);
}