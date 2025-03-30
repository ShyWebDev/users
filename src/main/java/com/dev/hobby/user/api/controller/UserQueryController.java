package com.dev.hobby.user.api.controller;

import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.application.query.handler.GetUserHandler;
import com.dev.hobby.user.common.CommonResponse;
import com.dev.hobby.user.mapper.command.UserQueryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users", description = "사용자 Read API 엔드포인트를 제공합니다")
@RestController
@RequestMapping("/api/vi/users")
@RequiredArgsConstructor
@Slf4j
public class UserQueryController {

    private final GetUserHandler getUserHandler;

    @Operation(summary = "사용자조회")
    @GetMapping("/{uniqueId}")
    public ResponseEntity<CommonResponse<UserQueryResponse>> getUserByUniqueId(@PathVariable String uniqueId){
        // 외부요청을 내부 명령 객체로 변환
        // 웹계층과 APP계층 분리
        // 객채 변환 로직을 중앙 집중하기 위해 MAPPER사용
        var getUserCmd = UserQueryMapper.toGetUserCmd(uniqueId);

        // 결과 캡슐화
        // 생성된 CMD 객체를 핸들러에 전달해 비즈니스 로직실행
        var getUserResult = getUserHandler.handle(getUserCmd);

        var response = UserQueryMapper.toUserQueryResponse(getUserResult);

        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
