package com.dev.hobby.user.domain.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetail {
    private String userId;
    private String nickname;
    private String mobileNumber;
    private String address;
}
