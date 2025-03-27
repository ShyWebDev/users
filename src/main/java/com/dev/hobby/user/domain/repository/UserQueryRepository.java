package com.dev.hobby.user.domain.repository;


import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;

import java.util.Optional;

/**
 * MongoDB에서 사용자 정보를 조회하는 리포지토리입니다.
 * 읽기 전용 작업을 처리합니다.
 */
public interface UserQueryRepository{

    UserDocument save(UserDocument userDocument);
    Optional<UserDocument> findByUniqueId(String uniqueId);
}
