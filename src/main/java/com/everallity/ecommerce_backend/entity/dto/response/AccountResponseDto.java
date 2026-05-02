package com.everallity.ecommerce_backend.entity.dto.response;

import java.util.Set;

public class AccountResponseDto {
    private long userId;
    private String email;
    private String password;
    private Set<String> roleSet;

    public AccountResponseDto(long userId, String email, String password, Set<String> roleSet) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roleSet = roleSet;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public AccountResponseDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
