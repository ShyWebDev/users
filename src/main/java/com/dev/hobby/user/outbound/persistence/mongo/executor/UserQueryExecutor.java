package com.dev.hobby.user.outbound.persistence.mongo.executor;

import com.dev.hobby.user.application.event.UserQuerySyncCompletedEvent;
import com.dev.hobby.user.domain.outbound.UserDetailQueryRepository;
import com.dev.hobby.user.domain.outbound.UserQueryRepository;
import com.dev.hobby.user.outbound.persistence.command.SaveUserCmd;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryExecutor {

    private final UserQueryRepository userQueryRepository;
    private final UserDetailQueryRepository userDetailQueryRepository;

    private final ApplicationEventPublisher eventPublisher;  // 사용자 이벤트를 발행하는 퍼블리셔

    @Transactional
    public void saveUserAndDetail(SaveUserCmd saveUserCmd) {
        userQueryRepository.save(saveUserCmd.getUser());
        if(saveUserCmd.getUserDetail()!= null)
            userDetailQueryRepository.save(saveUserCmd.getUserDetail());

        eventPublisher.publishEvent(UserQuerySyncCompletedEvent.builder()
                .user(saveUserCmd.getUser())
                .userDetail(saveUserCmd.getUserDetail())
                .build());
    }
}
