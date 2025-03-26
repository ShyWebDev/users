package com.dev.hobby.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserCmdResponse {
    private String uniqueId;

    @Schema(description = "callbackUrl은 필수값입니다." , example = "https://www.hunet.co.kr")
    private String callBackUrl;
}
