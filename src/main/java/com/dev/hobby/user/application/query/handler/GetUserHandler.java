package com.dev.hobby.user.application.query.handler;

import com.dev.hobby.user.api.dto.GetUserCmd;
import com.dev.hobby.user.api.dto.GetUserResult;
import com.dev.hobby.user.application.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 사용자 생성 명령을 처리하는 핸들러입니다.
 * 사용자 생성 요청을 받아 비즈니스 로직을 처리하고, 생성된 사용자 정보와 UUID를 반환합니다.
 */
@Component
@RequiredArgsConstructor
public class GetUserHandler {

    private final UserQueryService userQueryService;  // 도메인 서비스

    public GetUserResult handle(GetUserCmd getUserCmd) {
        return userQueryService.getUser(getUserCmd);
    }
}