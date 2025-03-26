package com.dev.hobby.user.domain;

import com.dev.hobby.user.exceptions.UserException;
import com.dev.hobby.user.repository.entity.UserDuplicationChecker;
import lombok.*;
import org.springframework.util.ObjectUtils;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain {
    private String uniqueId;
    private String email;
    private String password;


    public static void valid(String email, String password, UserDuplicationChecker userDuplicationChecker) {
        validPassword(password);
        UserDomain.existsByEmail(email, userDuplicationChecker);
    }


    public static UserDomain createUserDomain(String uniqueId, String email, String password) {
        try {
            Thread.sleep(3000);
            String test ="";
            Integer.parseInt(test);
            return UserDomain.builder()
                    .uniqueId(uniqueId)
                    .email(email)
                    .password(password)
                    .build();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
    }

    private static void existsByEmail(String email ,UserDuplicationChecker userDuplicationChecker){
        if(ObjectUtils.isEmpty(email))
            throw new UserException("이메일을 입력해주세요.");

        if(userDuplicationChecker.existsByEmail(email))
            throw new UserException("이미 존재하는 이메일입니다.");
    }

    private static void validPassword(String password){
        if(ObjectUtils.isEmpty(password))
            throw new UserException("비밀번호는 필수값입니다.");
    }
}
