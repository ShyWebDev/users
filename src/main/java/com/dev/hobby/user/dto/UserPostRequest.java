package com.dev.hobby.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserPostRequest {
    @NotBlank(message = "사용자 이름은 필수값입니다.")
    private String userName;

    @NotBlank(message = "이메일은 필수값입니다.")
    @Pattern(
            regexp = "^(?!\\.)(?!.*\\.\\.)([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    @Schema(description = "사용자 이메일", example = "a@hunet.co.kr")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

    @Schema(description = "callbackUrl은 필수값입니다." , example = "https://www.hunet.co.kr")
    private String callBackUrl;
}
