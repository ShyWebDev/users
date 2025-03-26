package com.dev.hobby.user.infrastructure.persistence.mysql.adapter;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
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
        UserEntity entity = UserEntity.builder()
                .uniqueId(userDomain.getUniqueId())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .name(userDomain.getName())
                .createdAt(userDomain.getCreatedAt())
                .build();

        UserEntity saved = jpaUserRepository.save(entity);

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
