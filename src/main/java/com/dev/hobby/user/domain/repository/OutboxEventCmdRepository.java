package com.dev.hobby.user.domain.repository;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.external.messaging.outbox.OutboxEventEntity;

import java.util.List;
import java.util.Optional;


public interface OutboxEventCmdRepository{

    void save(OutBoxEventDomain domain);
    Optional<OutboxEventEntity> findByUniqueId(String uniqueId);

    List<OutboxEventEntity> findTop50BySyncedAtIsNullOrderByCreatedAt();

    //List<OutboxEventEntity> findAllByStatusAndRetryCountLessThanEqual(String status, Integer retryCount);
    //List<OutboxEventEntity> findAllByStatus(String status);

}
