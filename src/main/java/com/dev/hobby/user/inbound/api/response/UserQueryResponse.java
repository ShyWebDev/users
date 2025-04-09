package com.dev.hobby.user.inbound.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * 사용자 조회 응답 DTO입니다.
 *
 * - GetUserHandler에서 조회된 사용자 정보를 반환하는 데 사용됩니다.
 */
public record UserQueryResponse(

    @Schema(description = "사용자 고유ID", example = "1")
    String userId,

    @Schema(description = "사용자 이메일", example = "alice@example.com")
    String email,

    @Schema(description = "사용자 이름", example = "Alice")
    String name,

    String nickname,
    String mobileNumber,
    String address,

    String callBackUrl,

    String status,

    LocalDateTime createdAt,
    LocalDateTime updatedAt
){}