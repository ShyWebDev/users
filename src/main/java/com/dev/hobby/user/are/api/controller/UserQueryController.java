package com.dev.hobby.user.are.api.controller;

import com.dev.hobby.user.are.api.dto.UserQueryResponse;
import com.dev.hobby.user.are.application.query.service.UserQueryService;
import com.dev.hobby.user.are.common.CommonResponse;
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

    private final UserQueryService UserQueryService;

    @Operation(summary = "사용자조회")
    @GetMapping("/{uniqueId}")
    public ResponseEntity<CommonResponse<UserQueryResponse>> getUserByUniqueId(@PathVariable String uniqueId){
        return ResponseEntity.ok(CommonResponse.success(UserQueryService.getUserByUniqueId(uniqueId)));
    }
}
