package com.dev.hobby.user.infrastructure.messaging.consumer;

import com.dev.hobby.user.application.sync.OutboxEventSyncService;
import com.dev.hobby.user.application.sync.UserSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class EntitySyncKafkaConsumer {

    private final OutboxEventSyncService outboxSyncService;
    private final UserSyncService userSyncService;


    /**
     * Kafka 메시지 수신 시 uniqueId 또는 타입에 따라 동기화 작업을 실행합니다.
     * 예시: 메시지 내용이 "OUTBOX:{uniqueId}" 또는 "USER:{userId}" 형태라고 가정
     */
    @KafkaListener(topics = "domain-sync-topic")
    public void handleSyncMessage(String message) {
        if (message.startsWith("OUTBOX:")) {
            String uniqueId = message.substring("OUTBOX:".length());
            // Outbox 이벤트에 대한 동기화 처리
            outboxSyncService.syncOutboxEvent();
        } else if (message.startsWith("USER:")) {
            String userId = message.substring("USER:".length());
            // 사용자 데이터 동기화 처리
            userSyncService.syncUsers();
        }
    }
}
