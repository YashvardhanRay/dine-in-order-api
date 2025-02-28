package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

public interface UserService {

  public UserResponse registration(RegistrationRequest userRequest);

  public User findUserById(long userId);

  public User updateUserById(long userId, User updatedUser);
}
