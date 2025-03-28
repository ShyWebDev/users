package com.dev.hobby.user.external.persistence.mysql.adapter;

import com.dev.hobby.user.mapper.infra.UserInfraMapper;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
import com.dev.hobby.user.external.persistence.mysql.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCmdRepositoryAdapter  implements UserCmdRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void save(UserDomain domain) {
        jpaUserRepository.save(UserInfraMapper.toUserEntity(domain));
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByUniqueId(String uniqueId) {
        return jpaUserRepository.findByUniqueId(uniqueId);
    }

    @Override
    public List<UserEntity> findTop50BySyncedAtIsNullOrderByCreatedAt() {
        return jpaUserRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
    }
}
