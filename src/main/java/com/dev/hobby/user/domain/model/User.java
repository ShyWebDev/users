package com.dev.hobby.user.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String userId;
    private String email;
    private String password;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
