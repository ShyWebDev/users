package com.dev.hobby.user.domain.service;

import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public User createUser(String userId, String email, String password, String name) {
        validateUserId(userId);
        validateEmail(email);
        validatePassword(password);
        validateName(name);

        String encryptedPassword = password; //passwordEncoder.encode(request.getPassword());
        return User.builder()
                .userId(userId)
                .email(email)
                .password(encryptedPassword)
                .name(name)
                .build();
    }

    private void validateUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new CustomException("User ID cannot be null or empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new CustomException("Email cannot be null or empty");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new CustomException("Password cannot be null or empty");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new CustomException("Name cannot be null or empty");
        }
    }
}
