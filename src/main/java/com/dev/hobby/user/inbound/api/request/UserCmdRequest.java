package com.dev.hobby.user.inbound.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCmdRequest (

    @NotBlank(message = "이메일은 필수값입니다.")
    @Pattern(
            regexp = "^(?!\\.)(?!.*\\.\\.)([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    @Schema(description = "사용자 이메일", example = "a@hunet.co.kr")
    String email,

    @NotBlank(message = "비밀번호는 필수값입니다.")
    String password,

    @NotBlank(message = "사용자 이름은 필수값입니다.")
    String name,

    String nickname,
    String mobileNumber,
    String address,

    @Schema(description = "callbackUrl" , example = "https://www.hunet.co.kr")
    String callBackUrl
){}
