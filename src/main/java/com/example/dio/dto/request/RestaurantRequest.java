package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    @NotEmpty(message = "Restaurant name cannot be null or blank !!")
    @NotBlank(message = "Restaurant name cannot be a space !!")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$" , message = "User can only contain Alphabets , Number and UnderScore")
    private String name;

    @NotEmpty(message = "User name cannot be null or blank !!")
    @NotBlank(message = "User name cannot be a space !!")
    private String address;

    @NotEmpty(message = "Phone no. cannot be null or blank !!")
    @NotBlank(message = "Phone no. cannot be a space !!")
    @Pattern(
            regexp = "^[0-9]\\d{10}$",
            message = "Invalid phone number format !! Example: 9876543210 (10 digits, starting with 0-9)")
    private String contactNumber;

    @NotEmpty(message = "Email cannot be empty !!")
    @NotBlank(message = "Email cannot be a space !!")
    @Email(message = "Invalid email format !! Example: user@example.com")
    @Size(max = 50, message = "Email must not exceed 50 characters !!")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com", message = "Email must be a valid Gmail address")
    private String contactEmail;

    @NotEmpty(message = "Opens-At  cannot be empty !!")
    @NotBlank(message = "Opens-At cannot be a space !!")
    @Pattern(regexp = "\\b(0?[1-9]|1[0-2]):[0-5][0-9] (AM|PM)\\b\n", message ="time should in proper format EX: 12:45 PM ")
    private LocalTime opensAt;

    @NotEmpty(message = "Close-At  cannot be empty !!")
    @NotBlank(message = "Close-At cannot be a space !!")
    private LocalTime closesAt;

    private List<DietType> dietTypes;

    private List<String> cuisineTypes;
}
