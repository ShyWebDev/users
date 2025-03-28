package com.dev.hobby.user.mapper.query;

import com.dev.hobby.user.api.dto.OutboxEventResponse;
import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.external.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.external.persistence.mongo.entity.OutboxEventDocument;
import lombok.experimental.UtilityClass;

/**
 * Document → DTO 변환 전담
 * CQRS: Query 전용 응답 Mapper
 * Clean Architecture: Infra 계층 Document → API 계층 DTO
 */
@UtilityClass
public class OutboxEventQueryMapper {

    public static OutboxEventResponse toDomain(OutboxEventDocument document) {
        return OutboxEventResponse.builder()
                .uniqueId(document.getUniqueId())
                .status(document.getStatus())
                .payload(document.getPayload())
                .build();
    }
    /*
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
     */
}