package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequest {

    @NotEmpty(message = "User name cannot be null or blank !!")
    @NotBlank(message = "User name cannot be a space !!")
    private String username;
    @NotEmpty(message = "Email cannot be empty !!")
    @NotBlank(message = "Email cannot be a space !!")
    @Email(message = "Invalid email format !! Example: user@example.com")
    @Size(max = 50, message = "Email must not exceed 50 characters !!")
    private String email;
    @NotEmpty(message = "Password cannot be null or blank !!")
    @NotBlank(message = "Password cannot be a space !!")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters !!")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character (@$!%*?&) !!")
    private String password;
    @NotEmpty(message = "Phone no. cannot be null or blank !!")
    @NotBlank(message = "Phone no. cannot be a space !!")
    @Pattern(
            regexp = "^[0-9]\\d{10}$",
            message = "Invalid phone number format !! Example: 9876543210 (10 digits, starting with 0-9)")
    private String phno;

    private UserRole role;
}
