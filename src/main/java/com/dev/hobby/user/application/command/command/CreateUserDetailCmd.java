package com.dev.hobby.user.application.command.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserDetailCmd {
    private String nickname;
    private String mobileNumber;
    private String address;
}
