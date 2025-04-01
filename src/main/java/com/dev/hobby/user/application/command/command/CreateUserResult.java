package com.dev.hobby.user.application.command.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder                        // 테스트시에 명확하게 사용하기위해
public class CreateUserResult {
    private String userId;

    @Schema(description = "callbackUrl은 필수값입니다." , example = "https://www.hunet.co.kr")
    private String callBackUrl;
}
