package com.dev.hobby.user.application.event;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserOutboxCommittedEvent {
    private User user;
    private UserDetail userDetail;
}
