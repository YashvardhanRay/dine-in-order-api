package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;

/**
 * It defines the service methods without exposing the business logic to the clint
 */
public interface UserService {


  /**
   * creating registration abstract method
   * which help in to registration for the user with data
   * includes RegistrationRequest
   * @param  userRequest -> UserRequest object
   * @return a UserResponse containing the user details
   */
  public UserResponse registration(RegistrationRequest userRequest);

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
   * @param  userId -> takes
   * @param updatedUser -> UserRequest Object containing updated values.
   */
  public UserResponse updateUserById(long userId, UserRequest updatedUser);
}
