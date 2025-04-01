package com.dev.hobby.user.domain.outbound;

public interface UserDuplicationChecker {
    boolean isDuplicated(String email);
}
