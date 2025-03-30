package com.dev.hobby.user.external.persistence.mysql.adapter;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.external.persistence.mysql.jpa.JpaUserRepository;
import com.dev.hobby.user.mapper.infra.UserInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserCmdRepositoryAdapter  implements UserCmdRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void save(UserDomain domain) {
        jpaUserRepository.save(UserInfraMapper.toUserEntity(domain));
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {
        if(jpaUserRepository.findByEmail(email).isPresent())
            return Optional.of(UserInfraMapper.toDomain(jpaUserRepository.findByEmail(email).get()));

        return Optional.empty();
    }

    @Override
    public Optional<UserDomain> findByUniqueId(String uniqueId) {
        var optional = jpaUserRepository.findByUniqueId(uniqueId);
        return optional.map(UserInfraMapper::toDomain);
    }

    @Override
    public List<UserDomain> findTop50BySyncedAtIsNullOrderByCreatedAt() {
        var entityList = jpaUserRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        return entityList.stream().map(UserInfraMapper::toDomain).collect(Collectors.toList());
    }
}
