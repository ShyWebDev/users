package com.dev.hobby.user.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class OutBoxEventDomain {
    private String uniqueId;
    private String eventType;
    private Long eventOrderNo;
    private String payload;
    private String status;
    private Integer retryCount;
}
