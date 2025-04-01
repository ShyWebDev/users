package com.dev.hobby.user.schedule;

import com.dev.hobby.user.domain.service.UserSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile({"local","default"})
public class EntitySyncSchedule {

    private final UserSyncService userSyncService;

    @Scheduled(fixedDelay = 10000)
    public void syncDomains() {
        //userSyncService.syncUsers();
    }
}
