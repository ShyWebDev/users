package com.dev.hobby.user.application.command.mapper;

/**
 * DTO와 도메인 객체(User) 간의 변환을 담당하는 매퍼 클래스입니다.
 *
 * - MapStruct를 사용하여 컴파일 시 자동으로 매핑 구현
 * - @Mapper 어노테이션을 사용하여 자동 매핑 코드 생성
 */

import com.dev.hobby.user.application.command.dto.UserPostRequest;
import com.dev.hobby.user.domain.model.UserDomain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    // DTO → Domain 변환
    public static UserDomain toDomain(String uniqueId, UserPostRequest dto) {
        return UserDomain.builder()
                .uniqueId(uniqueId)
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}