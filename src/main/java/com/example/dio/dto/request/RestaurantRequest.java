package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Restaurant name can only contain alphabets, numbers, and underscore !!")
    private String name;

    @NotEmpty(message = "Address cannot be null or blank !!")
    @NotBlank(message = "Address cannot be a space !!")
    private String address;

    @NotEmpty(message = "Phone number cannot be null or blank !!")
    @NotBlank(message = "Phone number cannot be a space !!")
    @Pattern(
            regexp = "^[7-9]\\d{9}$",
            message = "Invalid phone number format !! Example: 9876543210 (10 digits, starting with 7-9)")
    private String contactNumber;

    @NotEmpty(message = "Email cannot be empty !!")
    @NotBlank(message = "Email cannot be a space !!")
    @Email(message = "Invalid email format !! Example: user@example.com")
    @Size(max = 50, message = "Email must not exceed 50 characters !!")
    private String contactEmail;

    @NotNull(message = "Opens-At time cannot be null !!")
    @JsonFormat(pattern = "HH:mm:ss")  // 24-hour format: e.g., "10:00:00"
    private LocalTime opensAt;

    @NotNull(message = "Closes-At time cannot be null !!")
    @JsonFormat(pattern = "HH:mm:ss")  // 24-hour format: e.g., "22:00:00"
    private LocalTime closesAt;

    private List<DietType> dietTypes;

    private List<String> cuisineTypes;
}
