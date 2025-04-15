package com.example.dio.service.impl;

import com.example.dio.dto.request.UserRegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.IllegalRequestException;
import com.example.dio.exception.RestaurantNotFoundException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Restaurant;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.security.util.UserIdentity;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TableRepository tableRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserIdentity userIdentity;
    private final RestaurantRepository restaurantRepository;

    @Override
    public UserResponse registration(UserRegistrationRequest registrationRequest) {
        Admin user = new Admin();
        userMapper.mapToUserEntity(registrationRequest, user);

        encryptPassword(user);

        user.setRole(UserRole.ADMIN);

       return userMapper.mapToUserResponse( userRepository.save(user));
    }

/**
 * Produce and return child instance of the User Based on the user role.
 *
 * @param role the role of the user
 * @return User the parent reference containing either of Staff or Admin instance
 */
    private User createUserByRole (UserRole role){
        User user2;
        switch (role){
            case ADMIN -> user2 = new Admin();
            case STAFF -> user2 = new Staff();
            default -> throw new RuntimeException("Failed to register user, Invalid user type");
        }
        return user2;
    }

    /**
     * Find the user
     *
     * @param userId the id of the user
     * @return UserResponse object
     */
    @Override
    public UserResponse findUserById(long userId) {
        User user = userIdentity.getCurrentUser();
        userRepository.findById(Long.valueOf(user.getUserid()));
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest) {
        User user = userIdentity.getCurrentUser();
        userMapper.mapToUserRequest(userRequest,user);
        return userMapper.mapToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse staffRegistration(UserRegistrationRequest registrationRequest, long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "No restaurant found,Enter valid restaurant id"));

        if(restaurant.getCreatedBy().equals(userIdentity.getCurrentUserEmail())){
            Staff user = new Staff();
            userMapper.mapToUserEntity(registrationRequest, user);
            encryptPassword(user);
            user.setRole(UserRole.STAFF);
            user.setRestaurant(restaurant);
            user.setRestaurantTables(new ArrayList<>(restaurant.getRestaurantTables()));
            return userMapper.mapToUserResponse(userRepository.save(user));
        }
        else{
            throw new IllegalRequestException(
                    "Access denied: You are not authorized add staff at "+restaurant.getName());
        }
    }

    private void encryptPassword(User user){
        String encodedPassword =  passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

}
