package com.dev.hobby.user.outbound.messaging.publisher;

import com.dev.hobby.outbox.application.command.handler.SaveOutboxHandler;
import com.dev.hobby.user.outbound.messaging.message.UserCreatedEvent;
import com.dev.hobby.user.outbound.messaging.message.UserSyncEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserKafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String USER_SYNC_TOPIC = "user.sync";
    private static final String USER_CREATED_TOPIC = "user.created";


    private final SaveOutboxHandler saveOutboxHandler;  // 아웃박스 이벤트 생성 핸들러

    /**
     * 사용자 생성 이벤트를 Kafka 토픽으로 발행합니다.
     * @param event 발행할 사용자 생성 이벤트 객체
     */
    public void publishUserSyncEvent(UserSyncEvent event) {
        try {
            // 이벤트 객체를 JSON 문자열로 변환
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(USER_SYNC_TOPIC, event.userId(), eventJson).get();
            saveOutboxHandler.processPublishedHandlerByAggregateId(event.userId());
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }

    public void publishUserCreatedEvent(UserCreatedEvent event) {
        try {
            // 이벤트 객체를 JSON 문자열로 변환
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(USER_CREATED_TOPIC, event.userId(), eventJson).get();
            saveOutboxHandler.processCompletedHandlerByAggregateId(event.userId());
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }
}
