package com.example.dio.dto.constrains;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotEmpty(message = "can not be null or blank !!")
@NotBlank(message = "can not be blank !!")
@jakarta.validation.constraints.Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com", message = "Email must be a valid Gmail address")
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message()default "Email must be a valid Gmail address";
    Class<?>[] groups() default {} ;
    Class<?extends Payload>[] payload() default {};
}
