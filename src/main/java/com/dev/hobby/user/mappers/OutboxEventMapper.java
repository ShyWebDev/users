package com.dev.hobby.user.mappers;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import org.springframework.stereotype.Component;

@Component
public class OutboxEventMapper {

    public OutboxEventEntity toEntity(OutBoxEventDomain outBoxEventDomain) {
        return OutboxEventEntity.builder()
                .uniqueId(outBoxEventDomain.getUniqueId())
                .eventType(outBoxEventDomain.getEventType())
                .payload(outBoxEventDomain.getPayload())
                .status(outBoxEventDomain.getStatus())
                .retryCount(outBoxEventDomain.getRetryCount())
                .build();
    }

    public OutBoxEventDomain toDomain(OutboxEventEntity outboxEventEntity) {
        return OutBoxEventDomain.builder()
                .uniqueId(outboxEventEntity.getUniqueId())
                .eventType(outboxEventEntity.getEventType())
                .payload(outboxEventEntity.getPayload())
                .status(outboxEventEntity.getStatus())
                .retryCount(outboxEventEntity.getRetryCount())
                .build();
    }

    public OutBoxEventDomain documentToDomain(OutboxEventDocument outboxEventDocument) {
        return OutBoxEventDomain.builder()
                .uniqueId(outboxEventDocument.getUniqueId())
                .eventType(outboxEventDocument.getEventType())
                .payload(outboxEventDocument.getPayload())
                .status(outboxEventDocument.getStatus())
                .retryCount(outboxEventDocument.getRetryCount())
                .build();
    }
}
