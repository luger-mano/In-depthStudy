package com.indepthstudy.userservice.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TABLE_USERS_PROFILE")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(unique = true, nullable = false)
    private UUID authId;
    private String email;
    private String name;

    public UserProfile(UUID userId, UUID authId, String email, String name) {
        this.userId = userId;
        this.authId = authId;
        this.email = email;
        this.name = name;
    }

    public UserProfile() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAuthId() {
        return authId;
    }

    public void setAuthId(UUID authId) {
        this.authId = authId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
