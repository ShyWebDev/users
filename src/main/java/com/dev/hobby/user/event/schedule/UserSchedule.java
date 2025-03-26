package com.dev.hobby.user.event.schedule;

import com.dev.hobby.user.entitys.document.UserDocument;
import com.dev.hobby.user.entitys.entity.UserEntity;
import com.dev.hobby.user.repository.document.UserQueryRepository;
import com.dev.hobby.user.repository.entity.UserCmdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserSchedule {

    private final UserCmdRepository userCmdRepository;
    private final UserQueryRepository userQueryRepository;

    @Scheduled(fixedRate = 3000)
    @Transactional
    public void userSyncSchedule() {
        List<UserEntity> unSyncedEvents = userCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for(UserEntity unSyncedEvent : unSyncedEvents){
            Optional<UserDocument>  documentOpt = userQueryRepository.findByUniqueId(unSyncedEvent.getUniqueId());
            UserDocument document;
            if(documentOpt.isPresent()){
                document = documentOpt.get();
                document.setEmail(unSyncedEvent.getEmail());
                document.setPassword(unSyncedEvent.getPassword());
                document.setUpdatedAt(unSyncedEvent.getUpdatedAt());
            }
            else{
                document = UserDocument.builder()
                        .uniqueId(unSyncedEvent.getUniqueId())
                        .email(unSyncedEvent.getEmail())
                        .password(unSyncedEvent.getPassword())
                        .createdAt(unSyncedEvent.getCreatedAt())
                        .updatedAt(unSyncedEvent.getUpdatedAt())
                        .build();

            }
            userQueryRepository.save(document);

            unSyncedEvent.setSyncedAt(LocalDateTime.now());
            userCmdRepository.save(unSyncedEvent);
        }
    }
}
