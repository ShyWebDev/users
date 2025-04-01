package com.dev.hobby.user.external.persistence.service;

import com.dev.hobby.outbox.application.command.handler.SaveOutboxHandler;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.user.domain.outbound.UserCmdRepository;
import com.dev.hobby.user.external.messaging.publisher.UserKafkaPublisher;
import com.dev.hobby.user.external.persistence.command.SaveUserWithUpdateOutboxUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserExternalService {

    private final UserCmdRepository userCmdRepository;  // 사용자 데이터를 저장하는 리포지토리
    private final SaveOutboxHandler saveOutboxHandler;  // 아웃박스 이벤트 생성 핸들러

    private final UserKafkaPublisher userKafkaPublisher;  // 사용자 이벤트를 발행하는 퍼블리셔

    @Transactional
    public void saveUserAndUpdateOutbox(SaveUserWithUpdateOutboxUseCase saveUserWithUpdateOutboxUseCase) {
        userCmdRepository.save(saveUserWithUpdateOutboxUseCase.getUser());

        // 아웃박스 이벤트를 DB에 저장
        saveOutboxHandler.processSuccessHandler(saveUserWithUpdateOutboxUseCase.getOutboxId());

        //userKafkaPublisher.publishUserCreatedEvent(UserDomainEventMapper.toUserCreatedEvent(saveUserOutboxEvent.getUserDomain()));
    }
}
