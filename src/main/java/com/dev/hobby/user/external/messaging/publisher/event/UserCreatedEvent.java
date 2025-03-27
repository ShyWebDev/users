package com.dev.hobby.user.external.messaging.publisher.event;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserCreatedEvent {
    private String uniqueId;
    private String email;
    private String password;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}