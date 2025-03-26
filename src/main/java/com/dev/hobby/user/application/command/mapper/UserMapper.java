package com.dev.hobby.user.application.command.mapper;

/**
 * DTO와 도메인 객체(User) 간의 변환을 담당하는 매퍼 클래스입니다.
 *
 * - MapStruct를 사용하여 컴파일 시 자동으로 매핑 구현
 * - @Mapper 어노테이션을 사용하여 자동 매핑 코드 생성
 */

import com.dev.hobby.user.application.command.dto.UserPostRequest;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;

/**
 * DTO와 도메인 객체(User) 간의 변환을 수동으로 처리하는 매퍼 클래스입니다.
 *
 * - 수동으로 변환 메서드를 작성하여 MapStruct 없이 직접 매핑을 처리합니다.
 */
/**
 * DTO → Domain → Entity 변환을 위한 매퍼 클래스입니다.
 */
public class UserMapper {

    // DTO → Domain 변환
    public static UserDomain toDomain(String uniqueId, UserPostRequest dto) {
        return UserDomain.builder()
                .uniqueId(uniqueId)
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    // Domain → Entity 변환
    public static UserEntity toEntity(UserDomain domain) {
        return UserEntity.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .build();
    }

    // Entity → Domain 변환
    public static UserDomain toDomain(UserEntity entity) {
        return UserDomain.builder()
                .uniqueId(entity.getUniqueId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}