package com.dev.hobby.user.application.sync;

import com.dev.hobby.user.application.mapper.UserDomainMapper;
import com.dev.hobby.user.application.mapper.UserQueryMapper;
import com.dev.hobby.user.application.mapper.UserRepositoryMapper;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.spring.MongoUserRepository;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSyncService {

    private final UserCmdRepository userCmdRepository;
    private final UserQueryRepository userQueryRepository;

    public void syncUsers() {
        List<UserEntity> userEntityList = userCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (UserEntity userEntity : userEntityList) {
            Optional<UserDocument> userDocumentOpt = userQueryRepository.findByUniqueId(userEntity.getUniqueId());
            if (userDocumentOpt.isPresent()) {
                UserDocument userDocument = userDocumentOpt.get();
                userDocument.setEmail(userEntity.getEmail());
                userDocument.setPassword(userEntity.getPassword());
                userDocument.setName(userEntity.getName());
                userQueryRepository.save(userDocument);

                userEntity.setSyncedAt(LocalDateTime.now());
                userCmdRepository.save(userEntity);
            } else {
                UserDocument userDocument = UserQueryMapper.toDocument(userEntity);
                userQueryRepository.save(userDocument);

                userEntity.setSyncedAt(LocalDateTime.now());
                userCmdRepository.save(userEntity);
            }
        }
    }
}