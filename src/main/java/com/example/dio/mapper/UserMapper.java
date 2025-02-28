package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = UserResponse.builder()
                .userid(user.getUserid())
                .username(user.getUsername())
                .lastmodifiedat(user.getLastmodifiedat())
                .createdat(user.getCreatedat())
                .role(user.getUserrole())
                .build();

        return userResponse;
    }
    public static void mapToUserRequest(RegistrationRequest userRequest, User user) {
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhno(userRequest.getPhno());
        user.setUserrole(userRequest.getRole());
    }

    public static void mapToUserEntity(UserRequest updatedUser, User exuser) {
        exuser.setUsername(updatedUser.getUsername());
        exuser.setEmail(updatedUser.getEmail());
        exuser.setPhno(updatedUser.getPhno());
    }
}
