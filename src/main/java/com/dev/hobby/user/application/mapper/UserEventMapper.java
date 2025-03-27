package com.dev.hobby.user.application.mapper;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.external.messaging.publisher.event.UserCreatedEvent;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEventMapper {

    public static UserDomain toDomain(UserEntity entity) {
        return UserDomain.builder()
                .uniqueId(entity.getUniqueId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .build();
    }

    public static UserCreatedEvent toUserCreatedEvent(UserDomain domain) {
        return UserCreatedEvent.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}