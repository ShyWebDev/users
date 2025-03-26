package com.dev.hobby.user.repository.entity;

import com.dev.hobby.user.entitys.entity.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OutboxEventCmdRepository extends JpaRepository<OutboxEventEntity, String> {
    List<OutboxEventEntity> findTop50BySyncedAtIsNullOrderByCreatedAt();
    List<OutboxEventEntity> findAllByStatusAndRetryCountLessThanEqual(String status, Integer retryCount);
    List<OutboxEventEntity> findAllByStatus(String status);
}
