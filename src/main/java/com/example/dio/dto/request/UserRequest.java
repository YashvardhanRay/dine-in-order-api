package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "User name cannot be null or blank !!")
    @NotBlank(message = "User name cannot be a space !!")
    private String username;

    @NotEmpty(message = "Email cannot be empty !!")
    @NotBlank(message = "Email cannot be a space !!")
    @Email(message = "Invalid email format !! Example: user@example.com")
    @Size(max = 50, message = "Email must not exceed 50 characters !!")
    private String email;

    @NotEmpty(message = "Phone no. cannot be null or blank !!")
    @NotBlank(message = "Phone no. cannot be a space !!")
    @Pattern(
            regexp = "^[0-9]\\d{10}$",
            message = "Invalid phone number format !! Example: 9876543210 (10 digits, starting with 0-9)")
    private String phno;



}
