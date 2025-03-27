package com.dev.hobby.user.domain.repository;

import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;

import java.util.Optional;


public interface OutboxEventQueryRepository {
    OutboxEventDocument save(OutboxEventDocument outboxEventDocument);
    Optional<OutboxEventDocument> findByUniqueId(String uniqueId);

}
