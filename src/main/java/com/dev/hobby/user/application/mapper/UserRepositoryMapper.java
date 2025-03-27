package com.dev.hobby.user.application.mapper;

/**
 * DTO와 도메인 객체(User) 간의 변환을 담당하는 매퍼 클래스입니다.
 *
 * - MapStruct를 사용하여 컴파일 시 자동으로 매핑 구현
 * - @Mapper 어노테이션을 사용하여 자동 매핑 코드 생성
 */

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRepositoryMapper {
    // Domain → Entity 변환
    public static UserEntity toUserEntity(UserDomain userDomain) {
        return UserEntity.builder()
                .uniqueId(userDomain.getUniqueId())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .name(userDomain.getName())
                .build();
    }

}