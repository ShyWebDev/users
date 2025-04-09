package com.dev.hobby.user.mapper.command;

import com.dev.hobby.user.application.command.command.CreateUserCmd;
import com.dev.hobby.user.application.command.command.CreateUserDetailCmd;
import com.dev.hobby.user.application.command.command.CreateUserResult;
import com.dev.hobby.user.inbound.api.request.UserCmdRequest;
import com.dev.hobby.user.inbound.api.response.UserCmdResponse;
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
                .email(userCmdRequest.email())
                .password(userCmdRequest.password())
                .name(userCmdRequest.name())
                .userDetail(CreateUserDetailCmd.builder()
                        .nickname(userCmdRequest.nickname())
                        .mobileNumber(userCmdRequest.mobileNumber())
                        .address(userCmdRequest.address())
                        .build())
                .callBackUrl(userCmdRequest.callBackUrl())
                .build();
    }

    public static UserCmdResponse toUserCmdResponse(CreateUserResult createUserResult) {
        return new UserCmdResponse(
                createUserResult.getUserId(),
                createUserResult.getCallBackUrl()
        );
    }

}