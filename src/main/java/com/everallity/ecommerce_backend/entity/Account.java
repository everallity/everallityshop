package com.everallity.ecommerce_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String passwordHash;
    private String email;
    private LocalDateTime createdAt, updatedAt;

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roleSet;

    public Account() {
    }

    public Account(long id, String passwordHash, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Set<Role> roleSet) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleSet = roleSet;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public long getId() {
        return id;
    }

    public Account(String passwordHash, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.passwordHash = passwordHash;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
