package com.dev.hobby.user.domain.service;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.outbound.UserCmdRepository;
import com.dev.hobby.user.domain.outbound.UserQueryRepository;
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
        List<User> cmdDomainList = userCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (User cmdDomain : cmdDomainList) {
            try {
                processUsers(cmdDomain);
            } catch (Exception e) {
                log.error("Failed to sync user. userDomain={}", cmdDomain, e);
            }
        }
    }

    public void processUsers(User cmdDomain) {
        // MongoDB 저장/갱신
        Optional<User> existingQueryDomain =
                userQueryRepository.findByUserId(cmdDomain.getUserId());

        if (existingQueryDomain.isPresent()) {
            User queryDocument = existingQueryDomain.get();
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