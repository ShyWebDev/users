package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.outbound.messaging.message.UserCreatedEvent;
import com.dev.hobby.user.outbound.messaging.message.UserSyncEvent;
import com.dev.hobby.user.outbound.persistence.mongo.entity.UserDocument;
import lombok.experimental.UtilityClass;

/**
 * Domain → Kafka Event Object 변환
 * Event-Driven Architecture + DDD: 도메인 이벤트 → 외부 메시지 변환
 * Clean Architecture: 도메인은 메시징 시스템에 직접 의존하지 않음
 */
@UtilityClass
public class UserDomainEventMapper {

    public static UserSyncEvent toUserSyncEvent(User domain, UserDetail userDetail) {
        return new UserSyncEvent(
                domain.getUserId(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getName(),
                userDetail != null ? userDetail.getNickname() : null,
                userDetail != null ? userDetail.getMobileNumber() : null,
                userDetail != null ? userDetail.getAddress() : null);
    }

    public static UserCreatedEvent toUserCreatedEvent(User domain, UserDetail userDetail) {
        return new UserCreatedEvent(
                domain.getUserId(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getName(),
                userDetail != null ? userDetail.getNickname() : null,
                userDetail != null ? userDetail.getMobileNumber() : null,
                userDetail != null ? userDetail.getAddress() : null);
    }



    public static UserDocument toDocument(User domain) {
        return UserDocument.builder()
                .userId(domain.getUserId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
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
                .build();
    }



}