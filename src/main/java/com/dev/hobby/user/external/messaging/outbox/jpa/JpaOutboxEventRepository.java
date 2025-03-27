package com.dev.hobby.user.external.messaging.outbox.jpa;

import com.dev.hobby.user.external.messaging.outbox.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaOutboxEventRepository extends JpaRepository<OutboxEventEntity, String> {
    Optional<OutboxEventEntity> findByUniqueId(String uniqueId);
    List<OutboxEventEntity> findTop50BySyncedAtIsNullOrderByCreatedAt();
}
