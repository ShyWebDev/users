package com.dev.hobby.user.domain.outbound;


import com.dev.hobby.user.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 생성 및 업데이트와 같은 커맨드 작업을 처리하는 리포지토리입니다.
 */

public interface UserCmdRepository {
    void save(User domain);
    // 이메일로 사용자가 존재하는지 확인하는 메서드
    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(String userId);

    List<User> findTop50BySyncedAtIsNullOrderByCreatedAt();
}
