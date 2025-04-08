package com.example.dio.dto.constrains;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z].*[a-z])(?=.*\\d)(?=.*[@$!%*?#&]).{8,}$", message = "Password must be at least 8 characters long, contain at least one uppercase letter, at least two lowercase letters, at least one digit, and at least one special character (@$!%#*?&)")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Password {
    String message() default "Invaild password !!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
