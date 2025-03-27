package com.dev.hobby.user.application.command.handler;

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.api.dto.UserCmdResponse;
import com.dev.hobby.user.api.dto.UserCmdRequest;
import com.dev.hobby.user.application.command.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 사용자 생성 명령을 처리하는 핸들러입니다.
 * 사용자 생성 요청을 받아 비즈니스 로직을 처리하고, 생성된 사용자 정보와 UUID를 반환합니다.
 */
@Component
@RequiredArgsConstructor
public class CreateUserHandler {

    private final UserDomainService userDomainService;  // 도메인 서비스


    public CreateUserResult handle(CreateUserCmd createUserCmd) {
        return userDomainService.createUser(createUserCmd);
    }
}