package com.everallity.ecommerce_backend.model.dto.request;

import com.everallity.ecommerce_backend.validator.PasswordValidator;
import com.everallity.ecommerce_backend.validator.annotation.PasswordStrengthConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class AccountRequestDto {

    @Email(message = "EMAIL_INVALID")
    private String email;

    @PasswordStrengthConstraint(min = 8, message = "PASSWORD_LENGTH_INVALID")
    private String password;

    public AccountRequestDto(String userName, String password) {
        this.email = userName;
        this.password = password;
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
