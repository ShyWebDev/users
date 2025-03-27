package com.dev.hobby.user.application.mapper;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OutboxEventQueryMapper {

    public static OutboxEventDocument toDocument(OutboxEventEntity entity) {
        return OutboxEventDocument.builder()
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

    public static OutBoxEventDomain toEntity(OutboxEventEntity entity) {
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