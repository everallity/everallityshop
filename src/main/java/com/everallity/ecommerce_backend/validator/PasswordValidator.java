package com.everallity.ecommerce_backend.validator;

import com.everallity.ecommerce_backend.validator.annotation.PasswordStrengthConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordStrengthConstraint, String>{

    private int min;

    @Override
    public void initialize(PasswordStrengthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() >= min;
    }
}
