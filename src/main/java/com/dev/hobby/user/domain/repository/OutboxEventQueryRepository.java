package com.dev.hobby.user.domain.repository;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;

import java.util.List;
import java.util.Optional;


public interface OutboxEventQueryRepository {
    OutboxEventDocument save(OutboxEventDocument outboxEventDocument);
    Optional<OutboxEventDocument> findByUniqueId(String uniqueId);

}
