package com.dev.hobby.user.controller.api;

import com.dev.hobby.user.dto.CommonResponse;
import com.dev.hobby.user.dto.UserCmdResponse;
import com.dev.hobby.user.dto.UserPostRequest;
import com.dev.hobby.user.dto.UserResponse;
import com.dev.hobby.user.service.UserCmdService;
import com.dev.hobby.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Tag(name = "users", description = "사용자 REST API 엔드포인트를 제공합니다")
@RestController
@RequestMapping("/api/vi/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserCmdService userCmdService;
    private final UserQueryService UserQueryService;

    @Operation(summary = "사용자등록")
    @PostMapping("")
    public CompletableFuture<ResponseEntity<CommonResponse<UserCmdResponse>>> createUser(@Valid @RequestBody UserPostRequest userPostRequest){
        return userCmdService.registerUserByUserRequest(userPostRequest)
                .thenApply(result->ResponseEntity.ok(CommonResponse.success(result)));
    }

    @Operation(summary = "사용자조회")
    @GetMapping("/{uniqueId}")
    public ResponseEntity<CommonResponse<UserResponse>> getUser(@PathVariable String uniqueId){
        return ResponseEntity.ok(CommonResponse.success(UserQueryService.getUserByUniqueId(uniqueId)));
    }
}
