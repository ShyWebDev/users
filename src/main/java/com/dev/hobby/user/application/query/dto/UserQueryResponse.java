package com.dev.hobby.user.application.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 사용자 조회 응답 DTO입니다.
 *
 * - GetUserHandler에서 조회된 사용자 정보를 반환하는 데 사용됩니다.
 */
@Getter
@Builder
public class UserQueryResponse {

    @Schema(description = "사용자 고유ID", example = "1")
    private String uniqueId;

    @Schema(description = "사용자 이메일", example = "alice@example.com")
    private String email;

    @Schema(description = "사용자 이름", example = "Alice")
    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}