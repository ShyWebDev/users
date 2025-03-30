package com.dev.hobby.user.external.persistence.service;

import com.dev.hobby.outbox.application.handler.OutboxHandler;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.external.messaging.publisher.UserKafkaPublisher;
import com.dev.hobby.user.external.persistence.event.SaveUserOutboxEvent;
import com.dev.hobby.user.mapper.infra.UserDomainEventMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserExternalService {

    private final UserCmdRepository userCmdRepository;  // 사용자 데이터를 저장하는 리포지토리
    private final OutboxHandler outboxHandler;  // 아웃박스 이벤트 생성 핸들러

    private final UserKafkaPublisher userKafkaPublisher;  // 사용자 이벤트를 발행하는 퍼블리셔

    @Transactional
    public  void saveUserOutbox(SaveUserOutboxEvent saveUserOutboxEvent) {
        userCmdRepository.save(saveUserOutboxEvent.getUserDomain());

        saveUserOutboxEvent.getOutbox().setStatus(OutboxStatus.SUCCESS.toString());
        // 아웃박스 이벤트를 DB에 저장
        outboxHandler.handler(saveUserOutboxEvent.getOutbox());

        //userKafkaPublisher.publishUserCreatedEvent(UserDomainEventMapper.toUserCreatedEvent(saveUserOutboxEvent.getUserDomain()));
    }
}
