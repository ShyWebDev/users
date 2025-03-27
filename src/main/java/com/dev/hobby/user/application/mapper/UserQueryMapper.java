package com.dev.hobby.user.application.mapper;

import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserQueryMapper {

    // DTO → Domain 변환
    public static UserDomain toDomain(UserDocument document) {
        return UserDomain.builder()
                .uniqueId(document.getUniqueId())
                .email(document.getEmail())
                .password(document.getPassword())
                .name(document.getEmail())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }

    public static UserQueryResponse toResponse(UserDomain domain) {
        return UserQueryResponse.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .name(domain.getEmail())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}