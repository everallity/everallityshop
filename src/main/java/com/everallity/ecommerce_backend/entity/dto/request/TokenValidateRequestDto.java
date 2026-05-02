package com.everallity.ecommerce_backend.entity.dto.request;

public class TokenValidateRequestDto {
    private String token;

    public TokenValidateRequestDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
