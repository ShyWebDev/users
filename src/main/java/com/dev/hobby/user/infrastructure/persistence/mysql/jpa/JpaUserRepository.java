package com.dev.hobby.user.infrastructure.persistence.mysql.jpa;

import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
