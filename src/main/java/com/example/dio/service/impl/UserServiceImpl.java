package com.example.dio.service.impl;

import com.example.dio.enums.UserRole;
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
    public User registration(User user) {
        User user1 = this.createUserByRole(user.getUserrole());
        this.mapToNewUser(user,user1);
        return userRepository.save(user1);
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



}
