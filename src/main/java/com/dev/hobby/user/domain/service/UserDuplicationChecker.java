package com.dev.hobby.user.domain.service;

public interface UserDuplicationChecker {
    boolean isDuplicated(String email);
}
