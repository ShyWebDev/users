package com.dev.hobby.user.application.event.handler;

import com.dev.hobby.outbox.application.command.handler.SaveOutboxHandler;
import com.dev.hobby.user.application.event.UserOutboxCommittedEvent;
import com.dev.hobby.user.mapper.infra.UserDomainEventMapper;
import com.dev.hobby.user.outbound.messaging.publisher.UserKafkaPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserOutboxEventHandler {

    private final UserKafkaPublisher userKafkaPublisher;
    private final SaveOutboxHandler saveOutboxHandler;  // 아웃박스 이벤트 생성 핸들러

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserOutboxCommitted(UserOutboxCommittedEvent event) {
        try {
            saveOutboxHandler.processPublishingHandlerByAggregateId(event.getUser().getUserId());
            userKafkaPublisher.publishUserSyncEvent(UserDomainEventMapper.toUserSyncEvent(event.getUser(), event.getUserDetail()));
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }


}
