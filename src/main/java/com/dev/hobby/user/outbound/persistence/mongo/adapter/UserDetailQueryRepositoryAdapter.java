package com.dev.hobby.user.outbound.persistence.mongo.adapter;

import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.domain.outbound.UserDetailQueryRepository;
import com.dev.hobby.user.mapper.infra.UserDetailEventMapper;
import com.dev.hobby.user.outbound.persistence.mongo.spring.MongoUserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDetailQueryRepositoryAdapter implements UserDetailQueryRepository {

    private final MongoUserDetailRepository mongoUserDetailRepository;

    @Override
    public void save(UserDetail domain) {
        mongoUserDetailRepository.save(UserDetailEventMapper.toDocument(domain));
    }

    @Override
    public Optional<UserDetail> findByUserId(String userId) {
        var optional = mongoUserDetailRepository.findByUserId(userId);
        return optional.map(UserDetailEventMapper::toDomain);
    }
}