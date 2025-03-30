package com.dev.hobby.user.mapper.domain;

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.api.dto.GetUserResult;
import com.dev.hobby.user.domain.model.UserDomain;
import lombok.experimental.UtilityClass;

/**
 * Domain → Entity 변환 전담
 * CQRS: Command 전용 저장 흐름
 * Clean Architecture: 도메인 계층 → 인프라 계층 방향만 의존
 * DDD: 도메인 객체가 외부 기술(MySQL Entity)에 직접 의존하지 않도록 분리
 */
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

    public static GetUserResult toGetUserResult(UserDomain domain) {
        return GetUserResult.builder()
                .uniqueId(domain.getUniqueId())
                .email(domain.getEmail())
                .build();
    }

}