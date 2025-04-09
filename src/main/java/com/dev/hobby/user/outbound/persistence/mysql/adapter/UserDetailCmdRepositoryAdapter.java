package com.dev.hobby.user.outbound.persistence.mysql.adapter;

import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.domain.outbound.UserDetailCmdRepository;
import com.dev.hobby.user.mapper.infra.UserDetailInfraMapper;
import com.dev.hobby.user.outbound.persistence.mysql.jpa.JpaUserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDetailCmdRepositoryAdapter implements UserDetailCmdRepository {
    private final JpaUserDetailRepository jpaUserDetailRepository;

    @Override
    public void save(UserDetail domain) {
        jpaUserDetailRepository.save(UserDetailInfraMapper.toUserEntity(domain));
    }
}
