package com.indepth.authservice.adapter.out.repository;

import com.indepth.authservice.adapter.out.entities.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCredentialRepository extends JpaRepository<UserCredential, UUID> {

    Optional<UserCredential> findByEmail(String email);
}
