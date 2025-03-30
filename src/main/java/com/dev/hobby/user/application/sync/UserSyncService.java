package com.dev.hobby.user.application.sync;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSyncService {

    private final UserCmdRepository userCmdRepository;
    private final UserQueryRepository userQueryRepository;

    @Transactional
    public void syncUsers() {
        List<UserDomain> cmdDomainList = userCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (UserDomain cmdDomain : cmdDomainList) {
            try {
                processUsers(cmdDomain);
            } catch (Exception e) {
                log.error("Failed to sync user. userDomain={}", cmdDomain, e);
            }
        }
    }

    public void processUsers(UserDomain cmdDomain) {
        // MongoDB 저장/갱신
        Optional<UserDomain> existingQueryDomain =
                userQueryRepository.findByUniqueId(cmdDomain.getUniqueId());

        if (existingQueryDomain.isPresent()) {
            UserDomain queryDocument = existingQueryDomain.get();
            queryDocument.setEmail(cmdDomain.getEmail());
            queryDocument.setPassword(cmdDomain.getPassword());
            queryDocument.setName(cmdDomain.getName());
            queryDocument.setUpdatedAt(cmdDomain.getUpdatedAt());
            userQueryRepository.save(queryDocument);
        } else {
            userQueryRepository.save(cmdDomain);
        }

        cmdDomain.setSyncedAt(LocalDateTime.now());
        userCmdRepository.save(cmdDomain);
    }
}