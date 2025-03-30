package com.dev.hobby.user.mapper.query;

import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;
import lombok.experimental.UtilityClass;

/**
 * Document → DTO 변환 전담
 * CQRS: Query 전용 응답 Mapper
 * Clean Architecture: Infra 계층 Document → API 계층 DTO
 */
@UtilityClass
public class UserQueryMapper {

    public UserQueryResponse toResponse(UserDocument document) {
        return UserQueryResponse.builder()
                .uniqueId(document.getUniqueId())
                .email(document.getEmail())
                .name(document.getName())
                .rank(document.getRank())
                .build();
    }

    /*
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
    */

}