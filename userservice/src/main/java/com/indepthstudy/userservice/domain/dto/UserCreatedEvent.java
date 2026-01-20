package com.indepthstudy.userservice.domain.dto;

import java.util.UUID;

public class UserCreatedEvent {

    private UUID authId;
    private String email;
    private String name;

    public UserCreatedEvent(UUID authId, String email, String name) {
        this.authId = authId;
        this.email = email;
        this.name = name;
    }

    public UserCreatedEvent() {
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
