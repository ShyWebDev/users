package com.dev.hobby.user.application.sync;


import com.dev.hobby.user.application.mapper.OutboxEventQueryMapper;
import com.dev.hobby.user.domain.repository.OutboxEventCmdRepository;
import com.dev.hobby.user.domain.repository.OutboxEventQueryRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.messaging.outbox.mapper.OutboxEventMapper;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxEventSyncService {

    private final OutboxEventCmdRepository outboxEventCmdRepository;
    private final OutboxEventQueryRepository outboxEventQueryRepository;

    @Transactional
    public void syncOutboxEvent() {
        List<OutboxEventEntity> outboxEventEntityList = outboxEventCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (OutboxEventEntity outboxEventEntity : outboxEventEntityList) {
           try{
                processOutboxEvent(outboxEventEntity);
           }catch (Exception e){
               log.error("Failed to sync outbox event. outboxEventEntity={}", outboxEventEntity, e);
           }
        }
    }

    public void processOutboxEvent(OutboxEventEntity entity) {
        // MongoDB 저장/갱신
        Optional<OutboxEventDocument> existingDoc =
                outboxEventQueryRepository.findByUniqueId(entity.getUniqueId());

        if (existingDoc.isPresent()) {
            OutboxEventDocument doc = existingDoc.get();
            doc.setStatus(entity.getStatus());
            doc.setRetryCount(entity.getRetryCount());
            doc.setUpdatedAt(entity.getUpdatedAt());
            outboxEventQueryRepository.save(doc);
        } else {
            OutboxEventDocument newDoc = OutboxEventQueryMapper.toDocument(entity);
            outboxEventQueryRepository.save(newDoc);
        }

        entity.setSyncedAt(LocalDateTime.now());
        outboxEventCmdRepository.save(OutboxEventMapper.toDomain(entity));
    }
}
