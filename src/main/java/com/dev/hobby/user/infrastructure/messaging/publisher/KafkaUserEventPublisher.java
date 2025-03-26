package com.dev.hobby.user.infrastructure.messaging.publisher;

import com.dev.hobby.user.domain.OutBoxStatus;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventCmdRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaUserEventPublisher {
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
}
