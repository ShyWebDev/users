package com.dev.hobby.user.external.persistence.mysql.jpa;

import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUniqueId(String uniqueId);

    List<UserEntity> findTop50BySyncedAtIsNullOrderByCreatedAt();
}
