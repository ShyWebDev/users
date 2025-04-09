package com.dev.hobby.user.application.command.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserCmd {
    private String email;
    private String password;
    private String name;
    private CreateUserDetailCmd userDetail;
    private String callBackUrl;
}
