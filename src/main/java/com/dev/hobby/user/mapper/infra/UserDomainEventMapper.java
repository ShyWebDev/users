package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.external.messaging.event.UserCreatedEvent;
import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;
import lombok.experimental.UtilityClass;

/**
 * Domain → Kafka Event Object 변환
 * Event-Driven Architecture + DDD: 도메인 이벤트 → 외부 메시지 변환
 * Clean Architecture: 도메인은 메시징 시스템에 직접 의존하지 않음
 */
@UtilityClass
public class UserDomainEventMapper {

    public static UserCreatedEvent toUserCreatedEvent(User domain) {
        return UserCreatedEvent.builder()
                .uniqueId(domain.getUserId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                //.syncedAt(domain.getSyncedAt())
                .build();
    }


    public static UserDocument toDocument(User domain) {
        return UserDocument.builder()
                .userId(domain.getUserId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .syncedAt(domain.getSyncedAt())
                .build();
    }

    public static User toDomain(UserDocument document) {
        return User.builder()
                .userId(document.getUserId())
                .email(document.getEmail())
                .password(document.getPassword())
                .name(document.getName())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .syncedAt(document.getSyncedAt())
                .build();
    }



}