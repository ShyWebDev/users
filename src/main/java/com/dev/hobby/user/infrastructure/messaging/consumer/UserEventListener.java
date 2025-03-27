package com.dev.hobby.user.infrastructure.messaging.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventListener {
/*
    private final OutboxEventCmdRepository outboxEventCmdRepository;
    @Scheduled(fixedRate = 3000)
    @Transactional
    public void outBoxEventConsumer() {
        List<OutboxEventEntity> outboxEventEntityList = outboxEventCmdRepository.findAllByStatus(OutBoxStatus.PUBLISHING.toString());

        if(ObjectUtils.isEmpty(outboxEventEntityList))
            return;

        for(OutboxEventEntity outboxEventEntity : outboxEventEntityList) {
            try{
                OutBoxEventDomain outBoxEventDomain = outboxEventMapper.toDomain(outboxEventEntity);

                outboxEventEntity.setStatus(OutBoxStatus.PUBLISHED.toString());
                outboxEventEntity.setSyncedAt(null);
                outboxEventCmdRepository.save(outboxEventEntity);

                userCmdService.registerUserByOutboxEventDomain(outBoxEventDomain);
            }catch (Exception e){
                log.error("Error while processing outbox event", e);
            }
        }

    }
    */
}
