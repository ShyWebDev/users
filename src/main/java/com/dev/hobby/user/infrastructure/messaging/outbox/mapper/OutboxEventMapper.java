package com.dev.hobby.user.infrastructure.messaging.outbox.mapper;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OutboxEventMapper {
    public static OutboxEventEntity toEntity(OutBoxEventDomain domain) {
        return OutboxEventEntity.builder()
                .uniqueId(domain.getUniqueId())
                .eventType(domain.getEventType())
                .eventOrderNo(domain.getEventOrderNo())
                .payload(domain.getPayload())
                .status(domain.getStatus())
                .retryCount(domain.getRetryCount())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getCreatedAt())
                .syncedAt(domain.getCreatedAt())
                .build();
    }

    public static OutBoxEventDomain toDomain(OutboxEventEntity entity) {
        return OutBoxEventDomain.builder()
                .uniqueId(entity.getUniqueId())
                .eventType(entity.getEventType())
                .eventOrderNo(entity.getEventOrderNo())
                .payload(entity.getPayload())
                .status(entity.getStatus())
                .retryCount(entity.getRetryCount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .syncedAt(entity.getSyncedAt())
                .build();
    }
}
