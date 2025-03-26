package com.dev.hobby.user.domain.repository;


import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * MongoDB에서 사용자 정보를 조회하는 리포지토리입니다.
 * 읽기 전용 작업을 처리합니다.
 */
@Repository
public interface UserQueryRepository extends MongoRepository<UserDocument, String> {
    // 필요한 추가 쿼리를 정의할 수 있습니다. 예: 사용자 이메일로 검색
    UserDomain findByEmail(String email);

    Optional<UserDocument> findByUniqueId(String uniqueId);
}
