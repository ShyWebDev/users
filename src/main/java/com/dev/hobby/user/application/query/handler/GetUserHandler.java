package com.dev.hobby.user.application.query.handler;

import com.dev.hobby.user.application.command.dto.UserPostRequest;
import com.dev.hobby.user.interfaces.response.UserResponse;
import com.dev.hobby.user.domain.service.UserDomainService;
import com.dev.hobby.user.service.UserQueryService;
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

    /**
     * 사용자 생성 명령을 처리합니다.
     * @param request 사용자 생성 요청 DTO
     * @return 사용자 생성 응답 DTO
     */
    public UserResponse handleGetUserByUniqueId(String uniqueId) {
        // 비즈니스 로직을 도메인 서비스에 위임하여 사용자 생성
        return userQueryService.getUserByUniqueId(uniqueId);

    }
}