package com.dev.hobby.user.domain.repository;


import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 생성 및 업데이트와 같은 커맨드 작업을 처리하는 리포지토리입니다.
 */

public interface UserCmdRepository {
    UserEntity save(UserEntity userEntity);
    // 이메일로 사용자가 존재하는지 확인하는 메서드
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUniqueId(String uniqueId);

    List<UserEntity> findTop50BySyncedAtIsNullOrderByCreatedAt();
}
