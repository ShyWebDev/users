package com.dev.hobby.user.infrastructure.persistence.mongo.adapter;

import com.dev.hobby.user.domain.repository.UserQueryRepository;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.spring.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryAdapter implements UserQueryRepository {

    private final MongoUserRepository mongoUserRepository;

    @Override
    public UserDocument save(UserDocument userDocument) {
        return mongoUserRepository.save(userDocument);
    }

    @Override
    public Optional<UserDocument> findByUniqueId(String uniqueId) {
        return mongoUserRepository.findByUniqueId(uniqueId);
    }


}