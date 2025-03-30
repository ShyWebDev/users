package com.dev.hobby.user.mapper.command;

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.api.dto.UserCmdRequest;
import com.dev.hobby.user.api.dto.UserCmdResponse;
import lombok.experimental.UtilityClass;

/**
 * DTO → Command 변환 전용
 * CQRS: Command 흐름 전용 Mapper
 * Clean Architecture: API 계층 DTO → 도메인 계층 모델로 흐름
 * DDD: Domain 객체는 Builder를 통해 명확한 생성 책임 보장
 */
@UtilityClass
public class UserCmdMapper {

    public static CreateUserCmd toCreateUserCmd(UserCmdRequest userCmdRequest) {
        return CreateUserCmd.builder()
                .email(userCmdRequest.getEmail())
                .password(userCmdRequest.getPassword())
                .name(userCmdRequest.getName())
                .callBackUrl(userCmdRequest.getCallBackUrl())
                .build();
    }

    public static UserCmdResponse toUserCmdResponse(CreateUserResult createUserResult) {
        return UserCmdResponse.builder()
                .uniqueId(createUserResult.getUniqueId())
                .callBackUrl(createUserResult.getCallBackUrl())
                .build();
    }

}