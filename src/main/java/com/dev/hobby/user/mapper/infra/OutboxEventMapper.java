package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.external.messaging.outbox.OutboxEventEntity;
import lombok.experimental.UtilityClass;

/**
 * Outbox Domain ↔ Entity 양방향 변환
 * Event-Driven Architecture: Outbox 패턴 구현을 위한 핵심 매퍼
 * Clean Architecture + DDD: 외부 시스템 이벤트 발행을 위한 기술 분리
 */
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
