package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

   /* public static UserResponse mapToUserResponse(User user) {
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
    }*/

    /**
     *
     * create and return structure of UserResponse using user object details
     *
     * @param user user entity object
     * @return userResponse object to send restricted attribute
     */
    UserResponse mapToUserResponse(User user);

    /**
     *
     * used for set userRequest (DTO class to update) data in user object
     *
     * @param userRequest DTO class with updated user details
     * @param user object of user entity
     */
    void mapToUserRequest(UserRequest userRequest, @MappingTarget User user);

    /**
     *
     * used for set RegistrationRequest (DTO class to register) data in user object
     *
     * @param updatedUser DTO class object with user detail
     * @param exuser object of user entity
     */
    void mapToUserEntity(UserRegistrationRequest updatedUser, @MappingTarget User exuser);
    User mapToUserRequest(UserRegistrationRequest registrationRequest);
}
