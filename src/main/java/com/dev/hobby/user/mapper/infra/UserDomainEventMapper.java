package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.UserDomain;
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


    public static UserDocument toDocument(UserDomain domain) {
        return UserDocument.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .build();
    }

    public static UserDomain toDomain(UserDocument document) {
        return UserDomain.builder()
                .uniqueId(document.getUniqueId())
                .email(document.getEmail())
                .password(document.getPassword())
                .name(document.getName())
                .build();
    }



}