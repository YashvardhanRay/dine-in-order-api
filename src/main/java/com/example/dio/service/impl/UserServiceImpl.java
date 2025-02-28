package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.dio.mapper.UserMapper.mapToUserRequest;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse registration(RegistrationRequest userRequest) {
        User user = this.createUserByRole(userRequest.getRole());
        mapToUserRequest(userRequest, user);
        userRepository.save(user);
       return UserMapper.mapToUserResponse(user);
    }



    private User createUserByRole (UserRole role){
        User user2;
        switch (role){
            case ADMIN -> user2 = new Admin();
            case STAFF -> user2 = new Staff();
            default -> throw new RuntimeException("Failed to register user, Invalid user type");
        }
        return user2;
    }

    @Override
    public UserResponse findUserById(long userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by id " + userId));
        return UserMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(long userId, UserRequest updatedUser) {
        User exuser = userRepository.findById(userId)
                        .orElseThrow(() ->  new UserNotFoundByIdException("Failed to find user, user not found by id " + userId));
        UserMapper.mapToUserEntity(updatedUser, exuser);
        userRepository.save(exuser);
         return UserMapper.mapToUserResponse(exuser);
    }



}
