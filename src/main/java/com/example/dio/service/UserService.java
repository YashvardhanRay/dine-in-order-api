package com.example.dio.service;

import com.example.dio.model.User;

public interface UserService {
  public User registration(User user);

  public User findUserById(long userId);
}
