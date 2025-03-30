package com.dev.hobby.user.api.controller;

import com.dev.hobby.user.api.dto.UserCmdRequest;
import com.dev.hobby.user.api.dto.UserCmdResponse;
import com.dev.hobby.user.application.command.handler.CreateUserHandler;
import com.dev.hobby.user.common.CommonResponse;
import com.dev.hobby.user.mapper.command.UserCmdMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users", description = "사용자 Create, Update, Delete API 엔드포인트를 제공합니다")
@RestController
@RequestMapping("/api/vi/users")
@RequiredArgsConstructor
@Slf4j
public class UserCmdController {

    private final CreateUserHandler createUserHandler;

    @Operation(summary = "사용자등록")
    @PostMapping("")
    public ResponseEntity<CommonResponse<UserCmdResponse>> createUser(@Valid @RequestBody UserCmdRequest userCmdRequest){
        // 외부요청 DTO를 내부 명령 객체로 변환
        // 웹계층과 APP계층 분리
        // 객채 변환 로직을 중앙 집중하기 위해 MAPPER사용
        var createUserCmd = UserCmdMapper.toCreateUserCmd(userCmdRequest);

        // 결과 캡슐화
        // 생성된 CMD 객체를 핸들러에 전달해 비즈니스 로직실행
        var createUserResult = createUserHandler.handle(createUserCmd);

        var response = UserCmdMapper.toUserCmdResponse(createUserResult);

        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
