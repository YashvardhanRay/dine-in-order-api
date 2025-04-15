package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import jakarta.validation.Valid;

/**
 * It defines the service methods without exposing the business logic to the clint
 */
public interface UserService {


  /**
   * creating registration abstract method
   * which help in to registration for the user with data
   * includes RegistrationRequest
   * @param  registrationRequest -> UserRequest object
   * @return a UserResponse containing the user details
   */
  public UserResponse registration(UserRegistrationRequest registrationRequest);

  /**
   * creating registration abstract method
   * which help in to registration for the user with data
   * includes RegistrationRequest
   * @param  userId-> User id
   * @return a UserResponse containing the user details
   */
  public UserResponse findUserById(long userId);

  /**
   * creating registration abstract method
   * which help in to registration for the user with data
   * includes RegistrationRequest
   * @param updatedUser -> UserRequest Object containing updated values.
   */
  public UserResponse updateUserById(UserRequest updatedUser);

  UserResponse staffRegistration(@Valid UserRegistrationRequest registrationRequest, long id);

}
