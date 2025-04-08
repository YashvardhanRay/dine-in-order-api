package com.example.dio.security.util;

import com.example.dio.exception.IllegalActivityException;
import com.example.dio.exception.UserNotFoundException;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserIdentity {

    private final UserRepository userRepository;

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUserEmail(){
        return getAuthentication().getName();
    }

    public User getCurrentUser(){
        return userRepository.findByEmail(getCurrentUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found !!"));
    }
    public void validateOwnership(String ownerName){
        if(!getCurrentUserEmail().equals(ownerName))
            throw new IllegalActivityException("user is not allowed to modify resource request");
    }
}
