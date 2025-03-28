package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.external.messaging.publisher.event.UserCreatedEvent;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
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

    /*
    public static UserDomain toDomain(UserEntity entity) {
        return UserDomain.builder()
                .uniqueId(entity.getUniqueId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .build();
    }

     */


}