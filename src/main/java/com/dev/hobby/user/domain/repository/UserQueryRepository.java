package com.dev.hobby.user.domain.repository;


import com.dev.hobby.user.domain.model.UserDomain;

import java.util.Optional;

/**
 * MongoDB에서 사용자 정보를 조회하는 리포지토리입니다.
 * 읽기 전용 작업을 처리합니다.
 */
public interface UserQueryRepository{

    void save(UserDomain userDomain);
    Optional<UserDomain> findByUniqueId(String uniqueId);
}
