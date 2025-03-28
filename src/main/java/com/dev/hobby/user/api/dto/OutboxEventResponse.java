package com.dev.hobby.user.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 사용자 조회 응답 DTO입니다.
 *
 * - GetUserHandler에서 조회된 사용자 정보를 반환하는 데 사용됩니다.
 */
@Getter
@Builder
public class OutboxEventResponse {
    private String uniqueId;
    private String eventType;
    private Long eventOrderNo;
    private String payload;

    private String status;

    private Integer retryCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime syncedAt;
}