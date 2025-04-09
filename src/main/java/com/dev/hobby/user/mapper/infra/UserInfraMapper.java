package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.outbound.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

/**
 * ✅ Infra ↔ Domain 양방향 변환 전담
 * ✅ Clean Architecture: 허용된 영역 (Infra에서 내부 계층인 Domain을 참조 가능)
 * ✅ DDD: 도메인과 외부 기술(Entity) 사이를 분리
 */
@UtilityClass
public class UserInfraMapper {
    // Domain → Entity 변환
    public static UserEntity toUserEntity(User domain) {
        return UserEntity.builder()
                .userId(domain.getUserId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        return User.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}