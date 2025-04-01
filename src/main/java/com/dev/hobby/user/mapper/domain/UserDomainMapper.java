package com.dev.hobby.user.mapper.domain;

import com.dev.hobby.user.application.command.command.CreateUserCmd;
import com.dev.hobby.user.application.command.command.CreateUserResult;
import com.dev.hobby.user.application.query.command.GetUserResult;
import com.dev.hobby.user.domain.model.User;
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
    public static User toUserDomain(String userId, CreateUserCmd createUserCmd) {
        return User.builder()
                .userId(userId)
                .email(createUserCmd.getEmail())
                .password(createUserCmd.getPassword())
                .name(createUserCmd.getName())
                .build();
    }

    public static CreateUserResult toCreateUserResult(String userId, CreateUserCmd createUserCmd) {
        return CreateUserResult.builder()
                .userId(userId)
                .callBackUrl(createUserCmd.getCallBackUrl())
                .build();
    }

    public static GetUserResult toGetUserResult(User domain) {
        return GetUserResult.builder()
                .userId(domain.getUserId())
                .email(domain.getEmail())
                .build();
    }

}