package com.dev.hobby.user.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder                        // 테스트시에 명확하게 사용하기위해
public class GetUserResult {
    private String uniqueId;
    private String email;
    private String name;
    private String rank;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
