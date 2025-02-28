package com.example.dio.controller;

import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody User user) {
        user = userService.registration(user);
        return ResponseBuilder.success(HttpStatus.CREATED,"User Created", user);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<User>> findUserById (@PathVariable long userId){
        User user = userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK,"User Found",user);
    }

}
