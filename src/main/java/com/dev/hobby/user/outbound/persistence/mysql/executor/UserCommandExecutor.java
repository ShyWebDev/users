package com.dev.hobby.user.outbound.persistence.mysql.executor;

import com.dev.hobby.outbox.application.command.handler.SaveOutboxHandler;
import com.dev.hobby.user.application.event.UserOutboxCommittedEvent;
import com.dev.hobby.user.domain.outbound.UserCmdRepository;
import com.dev.hobby.user.domain.outbound.UserDetailCmdRepository;
import com.dev.hobby.user.outbound.persistence.command.SaveUserCmd;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandExecutor {

    private final UserCmdRepository userCmdRepository;  // 사용자 데이터를 저장하는 리포지토리
    private final UserDetailCmdRepository userDetailCmdRepository;

    private final SaveOutboxHandler saveOutboxHandler;  // 아웃박스 이벤트 생성 핸들러

    private final ApplicationEventPublisher eventPublisher;  // 사용자 이벤트를 발행하는 퍼블리셔

    @Transactional
    public void SaveUserAndDetailWithUpdateOutbox(SaveUserCmd saveUserCmd) {
        userCmdRepository.save(saveUserCmd.getUser());
        if(saveUserCmd.getUserDetail()!= null)
            userDetailCmdRepository.save(saveUserCmd.getUserDetail());

        saveOutboxHandler.processSuccessHandlerByAggregateId(saveUserCmd.getUser().getUserId());

        eventPublisher.publishEvent(UserOutboxCommittedEvent.builder()
                .user(saveUserCmd.getUser())
                .userDetail(saveUserCmd.getUserDetail())
                .build());
    }
}
