package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    private String email;
    @NotEmpty(message = "Password cannot be null or blank !!")
    @NotBlank(message = "Password cannot be a space !!")
    private String password;
    @NotEmpty(message = "Phone no. cannot be null or blank !!")
    @NotBlank(message = "Phone no. cannot be a space !!")
    private String phno;
    @NotEmpty(message = "User role cannot be null or blank !!")
    @NotBlank(message = "User role cannot be a space !!")
    private UserRole role;
}
