package com.dev.hobby.user.external.persistence.mongo.adapter;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.outbound.UserQueryRepository;
import com.dev.hobby.user.external.persistence.mongo.spring.MongoUserRepository;
import com.dev.hobby.user.mapper.infra.UserDomainEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryAdapter implements UserQueryRepository {

    private final MongoUserRepository mongoUserRepository;

    @Override
    public void save(User user) {
        mongoUserRepository.save(UserDomainEventMapper.toDocument(user));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        var optional = mongoUserRepository.findByUserId(userId);
        return optional.map(UserDomainEventMapper::toDomain);
    }


}