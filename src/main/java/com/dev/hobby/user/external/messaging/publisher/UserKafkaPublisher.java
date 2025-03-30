package com.dev.hobby.user.external.messaging.publisher;

import com.dev.hobby.user.external.messaging.event.UserCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
//@Profile("dev")
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
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, event.getUniqueId(), message);
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }
}
