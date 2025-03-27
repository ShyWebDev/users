package com.dev.hobby.user.infrastructure.persistence.mysql.adapter;

import com.dev.hobby.user.are.application.command.mapper.UserMapper;
import com.dev.hobby.user.are.domain.model.UserDomain;
import com.dev.hobby.user.are.domain.repository.UserCmdRepository;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import com.dev.hobby.user.infrastructure.persistence.mysql.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCmdRepositoryAdapter  implements UserCmdRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDomain save(UserDomain userDomain) {
        UserEntity saved = jpaUserRepository.save(UserMapper.toEntity(userDomain));

        return UserDomain.builder()
                .uniqueId(saved.getUniqueId())
                .email(saved.getEmail())
                .name(saved.getName())
                .password(saved.getPassword())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }
}
