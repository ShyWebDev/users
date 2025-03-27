package com.dev.hobby.user.application.mapper;

import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserQueryMapper {

    public static UserDocument toDocument(UserEntity entity) {
        return UserDocument.builder()
                .uniqueId(entity.getUniqueId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .syncedAt(entity.getSyncedAt())
                .build();
    }

    public static UserQueryResponse toResponse(UserDocument document) {
        return UserQueryResponse.builder()
                .uniqueId(document.getUniqueId())
                .email(document.getEmail())
                .name(document.getEmail())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }

    public static UserDomain toDomain(UserEntity domain) {
        return UserDomain.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .name(domain.getEmail())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }


}