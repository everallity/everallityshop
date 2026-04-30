package com.everallity.ecommerce_backend.model.dto.response;

import java.util.Set;

public class AuthenticationResponseDto {
    private String email;
    private String token;
    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public AuthenticationResponseDto(String email, String token, Set<String> roles) {
        this.email = email;
        this.token = token;
        this.roles = roles;
    }

    public AuthenticationResponseDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
