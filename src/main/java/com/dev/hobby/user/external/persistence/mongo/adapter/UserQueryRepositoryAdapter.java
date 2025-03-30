package com.dev.hobby.user.external.persistence.mongo.adapter;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
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
    public void save(UserDomain userDomain) {
        mongoUserRepository.save(UserDomainEventMapper.toDocument(userDomain));
    }

    @Override
    public Optional<UserDomain> findByUniqueId(String uniqueId) {
        var optional = mongoUserRepository.findByUniqueId(uniqueId);
        return optional.map(UserDomainEventMapper::toDomain);
    }


}