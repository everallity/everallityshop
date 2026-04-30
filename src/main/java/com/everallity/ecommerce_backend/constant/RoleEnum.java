package com.everallity.ecommerce_backend.constant;

public enum RoleEnum {
    ADMIN("ADMIN"), USER("USER");


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    RoleEnum(String name) {
        this.name = name;
    }
}
