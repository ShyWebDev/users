package com.dev.hobby.user.event.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutBoxEventSchedule{

    /*
    private final OutboxEventCmdRepository outboxEventCmdRepository;
    private final MongoOutboxEventRepository outboxEventQueryRepository;

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
            outboxEventCmdRepository.save(OutboxEventMapper.toDomain(unSyncedEvent));
        }
    }
*/



}
