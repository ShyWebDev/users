package com.dev.hobby.user.application.command.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserCmd {
    private String email;
    private String password;
    private String name;
    private String callBackUrl;
}
