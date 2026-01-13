package com.indepth.authservice.adapter.in.response;

import java.util.UUID;

public class UserCredentialResponseDTO {
    private UUID userId;
    private String name;


    public UserCredentialResponseDTO(UUID userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UserCredentialResponseDTO() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
