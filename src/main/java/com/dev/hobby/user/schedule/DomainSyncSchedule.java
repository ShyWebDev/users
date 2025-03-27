package com.dev.hobby.user.schedule;

import com.dev.hobby.user.application.sync.OutboxEventSyncService;
import com.dev.hobby.user.application.sync.UserSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile({"local","default"})
public class DomainSyncSchedule {

    private final OutboxEventSyncService outboxSyncService;
    private final UserSyncService userSyncService;


    /**
     * 10초마다 Outbox 이벤트와 사용자 데이터를 동기화
     */
    @Scheduled(fixedDelay = 10000)
    public void syncDomains() {
        outboxSyncService.syncOutboxEvents();
        userSyncService.syncUsers();
    }
}
