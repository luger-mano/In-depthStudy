package com.indepth.authservice.adapter.in.response;

public class LoginResponseDTO {
    private String accessToken;
    private Long expireIn;

    public LoginResponseDTO(String accessToken, Long expireIn) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getExpireIn() {
        return expireIn;
    }
}
