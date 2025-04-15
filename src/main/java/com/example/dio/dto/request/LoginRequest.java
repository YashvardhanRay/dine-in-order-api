package com.example.dio.dto.request;

import com.example.dio.dto.constrains.Password;
import jakarta.validation.constraints.Email;

public record LoginRequest(
        @Email
        String email,
        @Password
        String password
) {}
