package com.example.dio.dto.request;

import com.example.dio.dto.constrains.MinValue;
import com.example.dio.dto.constrains.Names;
import com.example.dio.dto.constrains.Password;
import com.example.dio.dto.constrains.PhoneNo;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    @NotBlank(message = "Username can not be null or blank !!")
    @MinValue
    @Names
    private String username;

    @Email(message = "email is not valid")
    private String email;

    @Password
    private String password;

    @PhoneNo
    private String phno;

}
