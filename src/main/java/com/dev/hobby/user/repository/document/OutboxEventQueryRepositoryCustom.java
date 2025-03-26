package com.dev.hobby.user.repository.document;

import com.dev.hobby.user.entitys.document.OutboxEventDocument;

import java.util.List;
import java.util.Optional;


public interface OutboxEventQueryRepositoryCustom{
    List<OutboxEventDocument> findAllByStatusAndRetryCountGreaterThan(String status, Integer retryCount);
    List<OutboxEventDocument> findAllByStatus(String status);
    Optional<OutboxEventDocument> findByUniqueId(String uniqueId);
}
