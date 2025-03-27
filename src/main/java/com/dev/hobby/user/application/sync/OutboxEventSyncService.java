package com.dev.hobby.user.application.sync;

import com.dev.hobby.user.application.mapper.OutboxEventQueryMapper;
import com.dev.hobby.user.application.mapper.UserQueryMapper;
import com.dev.hobby.user.domain.repository.OutboxEventCmdRepository;
import com.dev.hobby.user.domain.repository.OutboxEventQueryRepository;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.persistence.mongo.adapter.OutboxEventQueryRepositoryAdapter;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxEventSyncService {

    private final OutboxEventCmdRepository outboxEventCmdRepository;
    private final OutboxEventQueryRepository outboxEventQueryRepository;

    public void syncOutboxEvent() {
        List<OutboxEventEntity> outboxEventEntityList = outboxEventCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (OutboxEventEntity outboxEventEntity : outboxEventEntityList) {
            Optional<OutboxEventDocument> outboxEventDocumentOpt = outboxEventQueryRepository.findByUniqueId(outboxEventEntity.getUniqueId());
            if (outboxEventDocumentOpt.isPresent()) {
                OutboxEventDocument outboxEventDocument = outboxEventDocumentOpt.get();
                outboxEventDocument.setStatus(outboxEventEntity.getStatus());
                outboxEventDocument.setRetryCount(outboxEventEntity.getRetryCount());
                outboxEventDocument.setUpdatedAt(outboxEventEntity.getUpdatedAt());
                outboxEventQueryRepository.save(outboxEventDocument);

                outboxEventEntity.setSyncedAt(LocalDateTime.now());
                outboxEventCmdRepository.save(outboxEventEntity);
            } else {
                OutboxEventDocument outboxEventDocument = OutboxEventQueryMapper.toDocument(outboxEventEntity);
                outboxEventQueryRepository.save(outboxEventDocument);

                outboxEventEntity.setSyncedAt(LocalDateTime.now());
                outboxEventCmdRepository.save(outboxEventEntity);
            }
        }
    }
}
