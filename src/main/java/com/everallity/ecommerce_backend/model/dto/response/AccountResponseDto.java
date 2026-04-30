package com.everallity.ecommerce_backend.model.dto.response;

import com.everallity.ecommerce_backend.model.Role;

import java.util.Set;

public class AccountResponseDto {
    private long id;
    private String email;
    private String password;
    private Set<String> roleSet;

    public AccountResponseDto(long id, String email, String password, Set<String> roleSet) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
