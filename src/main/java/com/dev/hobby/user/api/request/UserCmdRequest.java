package com.dev.hobby.user.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 생성 요청을 위한 DTO 클래스.
 *
 * - 외부 API 요청 본문에서 JSON → 객체로 바인딩됨
 * - 이후 내부에서 CreateUserCommand로 변환되어 유즈케이스에 전달됨
 */
@Getter
@Builder                        // 테스트시에 명확하게 사용하기위해
@NoArgsConstructor              // Jackson 역직렬화를 위해 필요
@AllArgsConstructor             // @Builder 작동을 위해 필요
public class UserCmdRequest {

    @NotBlank(message = "이메일은 필수값입니다.")
    @Pattern(
            regexp = "^(?!\\.)(?!.*\\.\\.)([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    @Schema(description = "사용자 이메일", example = "a@hunet.co.kr")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

    @NotBlank(message = "사용자 이름은 필수값입니다.")
    private String name;

    @Schema(description = "callbackUrl은 필수값입니다." , example = "https://www.hunet.co.kr")
    private String callBackUrl;
}
