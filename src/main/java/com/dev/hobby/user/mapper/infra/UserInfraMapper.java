package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

/**
 * ✅ Infra ↔ Domain 양방향 변환 전담
 * ✅ Clean Architecture: 허용된 영역 (Infra에서 내부 계층인 Domain을 참조 가능)
 * ✅ DDD: 도메인과 외부 기술(Entity) 사이를 분리
 */
@UtilityClass
public class UserInfraMapper {
    // Domain → Entity 변환
    public static UserEntity toUserEntity(UserDomain domain) {
        return UserEntity.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .build();
    }

    public static UserDomain toDomain(UserEntity entity) {
        return UserDomain.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .build();
    }

}