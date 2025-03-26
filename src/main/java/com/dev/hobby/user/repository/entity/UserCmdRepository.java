package com.dev.hobby.user.repository.entity;


import com.dev.hobby.user.entitys.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCmdRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findTop50BySyncedAtIsNullOrderByCreatedAt();
}
