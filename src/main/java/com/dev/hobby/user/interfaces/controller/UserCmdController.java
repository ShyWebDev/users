package com.dev.hobby.user.interfaces.controller;

import com.dev.hobby.user.application.command.dto.UserPostRequest;
import com.dev.hobby.user.application.command.handler.CreateUserHandler;
import com.dev.hobby.user.interfaces.response.UserCmdResponse;
import com.dev.hobby.user.support.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users", description = "사용자 Create, Update, Delete API 엔드포인트를 제공합니다")
@RestController
@RequestMapping("/api/vi/users")
@RequiredArgsConstructor
@Slf4j
public class UserCmdController {

    private final CreateUserHandler createUserHandler;

    @Operation(summary = "사용자등록")
    @PostMapping("")
    public ResponseEntity<CommonResponse<UserCmdResponse>> createUser(@Valid @RequestBody UserPostRequest userPostRequest){
        // 비즈니스 로직 처리 (사용자 생성)
        UserCmdResponse response = createUserHandler.handle(userPostRequest);

        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
