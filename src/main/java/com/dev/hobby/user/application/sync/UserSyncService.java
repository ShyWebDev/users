package com.dev.hobby.user.application.sync;

import com.dev.hobby.user.mapper.infra.UserDomainEventMapper;
import com.dev.hobby.user.mapper.query.UserQueryMapper;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
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
        List<UserEntity> userEntityList = userCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (UserEntity userEntity : userEntityList) {
            try {
                processUsers(userEntity);
            } catch (Exception e) {
                log.error("Failed to sync user. userEntity={}", userEntity, e);
            }
        }
    }

    public void processUsers(UserEntity entity) {
        // MongoDB 저장/갱신
        Optional<UserDocument> existingDoc =
                userQueryRepository.findByUniqueId(entity.getUniqueId());

        if (existingDoc.isPresent()) {
            UserDocument doc = existingDoc.get();
            doc.setEmail(entity.getEmail());
            doc.setPassword(entity.getPassword());
            doc.setName(entity.getName());
            doc.setUpdatedAt(entity.getUpdatedAt());
            userQueryRepository.save(doc);
        } else {
            UserDocument newDoc = UserQueryMapper.toDocument(entity);
            userQueryRepository.save(newDoc);
        }

        entity.setSyncedAt(LocalDateTime.now());
        userCmdRepository.save(UserDomainEventMapper.toDomain(entity));
    }
}