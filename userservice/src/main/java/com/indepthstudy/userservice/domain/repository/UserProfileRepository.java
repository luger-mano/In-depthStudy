package com.indepthstudy.userservice.domain.repository;

import com.indepthstudy.userservice.domain.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    boolean existsByAuthId(UUID authId);
}
