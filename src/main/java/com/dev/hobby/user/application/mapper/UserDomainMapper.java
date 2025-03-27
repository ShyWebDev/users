package com.dev.hobby.user.application.mapper;

/**
 * DTO와 도메인 객체(User) 간의 변환을 담당하는 매퍼 클래스입니다.
 *
 * - MapStruct를 사용하여 컴파일 시 자동으로 매핑 구현
 * - @Mapper 어노테이션을 사용하여 자동 매핑 코드 생성
 */

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.domain.model.UserDomain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDomainMapper {

    // Domain → Entity 변환
    public static UserDomain toUserDomain(String uniqueId, CreateUserCmd createUserCmd) {
        return UserDomain.builder()
                .uniqueId(uniqueId)
                .email(createUserCmd.getEmail())
                .password(createUserCmd.getPassword())
                .name(createUserCmd.getName())
                .build();
    }

    public static CreateUserResult toCreateUserResult(String uniqueId, CreateUserCmd createUserCmd) {
        return CreateUserResult.builder()
                .uniqueId(uniqueId)
                .callBackUrl(createUserCmd.getCallBackUrl())
                .build();
    }

}