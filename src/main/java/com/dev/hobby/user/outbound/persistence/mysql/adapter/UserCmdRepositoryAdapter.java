package com.dev.hobby.user.outbound.persistence.mysql.adapter;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.outbound.UserCmdRepository;
import com.dev.hobby.user.mapper.infra.UserInfraMapper;
import com.dev.hobby.user.outbound.persistence.mysql.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCmdRepositoryAdapter  implements UserCmdRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void save(User domain) {
        jpaUserRepository.save(UserInfraMapper.toUserEntity(domain));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if(jpaUserRepository.findByEmail(email).isPresent())
            return Optional.of(UserInfraMapper.toDomain(jpaUserRepository.findByEmail(email).get()));

        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        var optional = jpaUserRepository.findByUserId(userId);
        return optional.map(UserInfraMapper::toDomain);
    }
}
