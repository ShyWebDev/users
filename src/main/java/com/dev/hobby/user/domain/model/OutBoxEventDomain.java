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

    public static OutBoxEventDomain createOutboxDomain(String uniqueId, String eventType, String payload){
        return OutBoxEventDomain.builder()
                .uniqueId(uniqueId)
                .eventType(eventType)
                .eventOrderNo(0L)
                .payload(payload)
                .retryCount(0)
                .build();
    }
}
