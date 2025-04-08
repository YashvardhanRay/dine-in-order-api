package com.example.dio.controller;

import com.example.dio.dto.request.UserRegistrationRequest;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
@Tag(name = "User Controller", description = "Collection of API Endpoints for User Management.")
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
    public ResponseEntity<ResponseStructure<UserResponse>> registration(@Valid @RequestBody  UserRegistrationRequest registrationRequest) {
        UserResponse registration = userService.registration(registrationRequest);
        return ResponseBuilder.created("Data Stored!!", registration);
    }

    @PostMapping("/staff-register/restaurants/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseStructure<UserResponse>> staffRegistration(@Valid @RequestBody UserRegistrationRequest registrationRequest, @PathVariable long id){
        UserResponse registration = userService.staffRegistration(registrationRequest,id);
        return ResponseBuilder.created( "Data Stored !!",registration);
    }

    @GetMapping("/users/{userId}")
    @Operation(description = """
            The API Endpoint is used to Find user.
            The endpoints requires the userId.
            """,
            responses = {
                    @ApiResponse(responseCode = "201",description = "User Created"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            }
    )
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById (@PathVariable long userId){
        UserResponse user = userService.findUserById(userId);
        return ResponseBuilder.ok("User Found",user);
    }

    @PostMapping("user")
    @Operation(description = """
            The API Endpoint is used to update user details.
            The endpoints requires the user to select one of the specified role along with the other details.            
            """,
            responses = {
                    @ApiResponse(responseCode = "200" ,description = "User Updated"),
                    @ApiResponse(responseCode = "401" , description = "Invaild Input" , content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            }
    )
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody @Valid UserRequest updatedUser){
        UserResponse user = userService.updateUserById(updatedUser);
        return ResponseBuilder.ok("User Updated",user);
    }

}
