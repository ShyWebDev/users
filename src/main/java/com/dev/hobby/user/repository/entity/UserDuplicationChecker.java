package com.dev.hobby.user.repository.entity;

public interface UserDuplicationChecker {
    boolean existsByEmail(String email);
    // 추가해서 사용
}
