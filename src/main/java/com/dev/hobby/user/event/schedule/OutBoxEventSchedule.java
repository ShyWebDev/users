package com.dev.hobby.user.event.schedule;

import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventCmdRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.mappers.OutboxEventMapper;
import com.dev.hobby.user.repository.document.OutboxEventQueryRepository;
import com.dev.hobby.user.service.UserCmdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutBoxEventSchedule{

    private final OutboxEventCmdRepository outboxEventCmdRepository;
    private final OutboxEventQueryRepository outboxEventQueryRepository;

    private final OutboxEventMapper outboxEventMapper;

    private final UserCmdService userCmdService;

    @Scheduled(fixedRate = 3000)
    @Transactional
    public void outBoxEventSyncSchedule() {
        List<OutboxEventEntity> unSyncedEvents = outboxEventCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for(OutboxEventEntity unSyncedEvent : unSyncedEvents){
            Optional<OutboxEventDocument>  documentOpt = outboxEventQueryRepository.findByUniqueId(unSyncedEvent.getUniqueId());
            OutboxEventDocument document;
            if(documentOpt.isPresent()){
                document = documentOpt.get();
                document.setStatus(unSyncedEvent.getStatus());
                document.setRetryCount(unSyncedEvent.getRetryCount());
                document.setUpdatedAt(unSyncedEvent.getUpdatedAt());
            }
            else{
                document = OutboxEventDocument.builder()
                        .uniqueId(unSyncedEvent.getUniqueId())
                        .eventType(unSyncedEvent.getEventType())
                        .eventOrderNo(unSyncedEvent.getEventOrderNo())
                        .payload(unSyncedEvent.getPayload())
                        .status(unSyncedEvent.getStatus())
                        .retryCount(unSyncedEvent.getRetryCount())
                        .createdAt(unSyncedEvent.getCreatedAt())
                        .updatedAt(unSyncedEvent.getUpdatedAt())
                        .build();

            }
            outboxEventQueryRepository.save(document);
            unSyncedEvent.setSyncedAt(LocalDateTime.now());
            outboxEventCmdRepository.save(unSyncedEvent);
        }
    }




}
