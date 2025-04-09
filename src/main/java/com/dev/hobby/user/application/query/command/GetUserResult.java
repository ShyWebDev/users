package com.dev.hobby.user.application.query.command;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder                        // 테스트시에 명확하게 사용하기위해
public class GetUserResult {
    private boolean exists;
    private User user;
    private UserDetail userDetail;
    private Outbox outbox;
}
