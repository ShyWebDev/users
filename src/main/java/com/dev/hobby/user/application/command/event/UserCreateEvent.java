package com.dev.hobby.user.application.command.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateEvent {
    private String userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String mobileNumber;
    private String address;
    private String callBackUrl;

    private String outboxId;
}
