package com.dev.hobby.user.infrastructure.messaging.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaUserEventPublisher {
    /*
    private final OutboxEventCmdRepository outboxEventCmdRepository;

    @Scheduled(fixedRate = 3000)
    @Transactional
    public void outBoxEventProducer() {
        List<OutboxEventEntity> outboxEventEntityList = outboxEventCmdRepository.findAllByStatusAndRetryCountLessThanEqual(OutBoxStatus.RECEIVED.toString(),3);

        if(ObjectUtils.isEmpty(outboxEventEntityList))
            return;

        for(OutboxEventEntity outboxEventEntity : outboxEventEntityList) {
            log.info("1.outBoxEventProducer");
            outboxEventEntity.setStatus(OutBoxStatus.PUBLISHING.toString());
            outboxEventEntity.setSyncedAt(null);
        }
    }
    */
}
