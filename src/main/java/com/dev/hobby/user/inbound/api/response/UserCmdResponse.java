package com.dev.hobby.user.inbound.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

                    // 테스트시에 명확하게 사용하기위해
public record UserCmdResponse(
    String userId,

    @Schema(description = "callbackUrl은 필수값입니다." , example = "https://www.hunet.co.kr")
    String callBackUrl
){}
