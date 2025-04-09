package com.dev.hobby.user.domain.outbound;


import com.dev.hobby.user.domain.model.UserDetail;

/**
 * 사용자 생성 및 업데이트와 같은 커맨드 작업을 처리하는 리포지토리입니다.
 */

public interface UserDetailCmdRepository {
    void save(UserDetail domain);
}
