package com.dev.hobby.user.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class OutBoxEventDomain {
    private String uniqueId;
    private String eventType;
    private Long eventOrderNo;
    private String payload;

    @Setter
    private String status;

    @Setter
    private Integer retryCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Setter
    private LocalDateTime syncedAt;
}
