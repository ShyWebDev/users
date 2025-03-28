package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.external.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.external.persistence.mongo.entity.OutboxEventDocument;
import lombok.experimental.UtilityClass;

/**
 * Mongo Document ↔ Domain 양방향 변환
 * Query 기반 Outbox 흐름
 * Clean Architecture: 기술에 대한 도메인 독립성 유지
 */
@UtilityClass
public class OutBoxEventInfraMapper {

    public OutBoxEventDomain toDomain(OutboxEventDocument document) {
        return OutBoxEventDomain.builder()
                .id(document.getId())
                .eventType(document.getEventType())
                .status(document.getStatus())
                .payload(document.getPayload())
                .build();
    }

    public OutboxEventDocument toDocument(OutBoxEventDomain domain) {
        return OutboxEventDocument.builder()
                .id(domain.getId())
                .eventType(domain.getEventType())
                .status(domain.getStatus())
                .payload(domain.getPayload())
                .build();
    }
    /*
    public static OutboxEventEntity toEntity(OutBoxEventDomain domain) {
        return OutboxEventEntity.builder()
                .eventType(domain.getUniqueId())
                .eventOrderNo(domain.getEventOrderNo())
                .payload(domain.getPayload())
                .status(domain.getStatus())
                .retryCount(domain.getRetryCount())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .syncedAt(domain.getSyncedAt())
                .build();
    }

     */

}