package com.dev.hobby.user.mappers;

import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDomain userDomain) {
        return UserEntity.builder()
                .uniqueId(userDomain.getUniqueId())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .build();
    }

    public UserDomain toDomain(UserEntity userEntity) {
        return UserDomain.builder()
                .uniqueId(userEntity.getUniqueId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
