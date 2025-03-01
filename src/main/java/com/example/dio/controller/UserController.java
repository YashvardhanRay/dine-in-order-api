package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.service.UserService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid RegistrationRequest userRequest) {
       UserResponse userResponse = userService.registration(userRequest);
        return ResponseBuilder.success(HttpStatus.CREATED,"User Created", userResponse);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById (@PathVariable long userId){
        UserResponse user = userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK,"User Found",user);
    }

    @PostMapping("update/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable long userId, @RequestBody @Valid UserRequest updatedUser){
        UserResponse user = userService.updateUserById(userId, updatedUser);
        return ResponseBuilder.success(HttpStatus.OK,"User Updated",user);
    }

}
