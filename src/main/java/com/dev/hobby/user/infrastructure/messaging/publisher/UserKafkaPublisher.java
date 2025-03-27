package com.dev.hobby.user.infrastructure.messaging.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class UserKafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "user-events";

    /**
     * 사용자 생성 이벤트를 Kafka 토픽으로 발행합니다.
     * @param event 발행할 사용자 생성 이벤트 객체
     */
    public void publishUserCreatedEvent(UserCreatedEvent event) {
        try {
            // 이벤트 객체를 JSON 문자열로 직렬화
            String message = objectMapper.writeValueAsString(event);
            // Kafka 토픽으로 메시지 발행 (key로 uniqueId 사용)
            kafkaTemplate.send(TOPIC, event.getUniqueId(), message);
            log.info("Published user created event with id: {}", event.getUniqueId());
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }
}
