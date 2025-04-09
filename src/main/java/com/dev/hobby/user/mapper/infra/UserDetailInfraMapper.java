package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.outbound.persistence.mysql.entity.UserDetailEntity;
import lombok.experimental.UtilityClass;

/**
 * ✅ Infra ↔ Domain 양방향 변환 전담
 * ✅ Clean Architecture: 허용된 영역 (Infra에서 내부 계층인 Domain을 참조 가능)
 * ✅ DDD: 도메인과 외부 기술(Entity) 사이를 분리
 */
@UtilityClass
public class UserDetailInfraMapper {
    // Domain → Entity 변환
    public static UserDetailEntity toUserEntity(UserDetail domain) {
        return UserDetailEntity.builder()
                .userId(domain.getUserId())
                .nickname(domain.getNickname())
                .mobileNumber(domain.getMobileNumber())
                .address(domain.getAddress())
                .build();
    }

}