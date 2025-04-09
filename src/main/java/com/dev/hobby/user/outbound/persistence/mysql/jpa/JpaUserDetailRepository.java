package com.dev.hobby.user.outbound.persistence.mysql.jpa;

import com.dev.hobby.user.outbound.persistence.mysql.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserDetailRepository extends JpaRepository<UserDetailEntity, String> {
}
