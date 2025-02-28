package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
    private String phno;
    private UserRole role;
}
