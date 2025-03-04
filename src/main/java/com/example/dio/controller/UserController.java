package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.service.UserService;
import com.example.dio.utility.FieldErrorResponse;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(description = """
            The API Endpoint is used to register user.
            The endpoints requires the user to select one of the specified role along with the other details
            """,
            responses = {
                    @ApiResponse(responseCode = "201",description = "User Created"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            }
    )
    public ResponseEntity<ResponseStructure<UserResponse>> registration(@Valid @RequestBody  RegistrationRequest registrationRequest) {
        System.out.println("user name :" + registrationRequest.getUsername());
        UserResponse registration = userService.registration(registrationRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "Data Stored!!", registration);
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
