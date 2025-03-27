package com.dev.hobby.user.infrastructure.persistence.mongo.adapter;

import com.dev.hobby.user.are.application.query.mapper.UserQueryMapper;
import com.dev.hobby.user.are.domain.model.UserDomain;
import com.dev.hobby.user.are.domain.repository.UserQueryRepository;
import com.dev.hobby.user.infrastructure.persistence.mongo.spring.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryAdapter implements UserQueryRepository {

    private final MongoUserRepository mongoUserRepository;

    @Override
    public Optional<UserDomain> findByUniqueId(String uniqueId) {
        return mongoUserRepository.findById(uniqueId)
                .map(UserQueryMapper::toDomain);
    }
}