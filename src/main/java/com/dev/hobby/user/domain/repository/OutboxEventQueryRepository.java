package com.dev.hobby.user.domain.repository;

import com.dev.hobby.user.external.persistence.mongo.entity.OutboxEventDocument;

import java.util.Optional;


public interface OutboxEventQueryRepository {
    OutboxEventDocument save(OutboxEventDocument outboxEventDocument);
    Optional<OutboxEventDocument> findByUniqueId(String uniqueId);

}
