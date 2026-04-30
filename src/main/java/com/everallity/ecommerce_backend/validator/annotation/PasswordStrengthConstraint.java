package com.everallity.ecommerce_backend.validator.annotation;

import com.everallity.ecommerce_backend.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordStrengthConstraint {

    String message() default "Password invalid";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
