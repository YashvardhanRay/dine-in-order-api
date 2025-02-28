package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse registration(RegistrationRequest userRequest) {
        User user = this.createUserByRole(userRequest.getRole());
        mapToUserRequest(userRequest, user);
        userRepository.save(user);
       return mapToUserResponse(user);
    }

    private static UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = UserResponse.builder()
                .userid(user.getUserid())
                .username(user.getUsername())
                .lastmodifiedat(user.getLastmodifiedat())
                .createdat(user.getCreatedat())
                .role(user.getUserrole())
                .build();

        System.out.println(userResponse);
        return userResponse;
    }

    private static void mapToUserRequest(RegistrationRequest userRequest, User user) {
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhno(userRequest.getPhno());
        user.setUserrole(userRequest.getRole());
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

    private void mapToNewUser (User user, User user2){
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setUsername(user.getUsername());
        user2.setPhno(user.getPhno());
        user2.setUserrole(user.getUserrole());
    }

    @Override
    public UserResponse findUserById(long userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by id " + userId));
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(long userId, UserRequest updatedUser) {
        User exuser = userRepository.findById(userId)
                        .orElseThrow(() ->  new UserNotFoundByIdException("Failed to find user, user not found by id " + userId));
        exuser.setUsername(updatedUser.getUsername());
        exuser.setEmail(updatedUser.getEmail());
        exuser.setPhno(updatedUser.getPhno());
         userRepository.save(exuser);
         return mapToUserResponse(exuser);
    }

}
