package com.example.dio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "User name cannot be null or blank !!")
    @NotBlank(message = "User name cannot be a space !!")
    private String username;
    @NotEmpty(message = "Email cannot be null or blank !!")
    @NotBlank(message = "Email cannot be a space !!")
    private String email;
    @NotEmpty(message = "Phone number cannot be null or blank !!")
    @NotBlank(message = "Phone no. cannot be a space !!")
    private String phno;

}
