package com.dev.hobby.user.application.command.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder                        // 테스트시에 명확하게 사용하기위해
public class CreateUserResult {
    private String userId;
    private String callBackUrl;
}
