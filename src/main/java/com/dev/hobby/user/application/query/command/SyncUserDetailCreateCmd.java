package com.dev.hobby.user.application.query.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SyncUserDetailCreateCmd {
    private String nickname;
    private String mobileNumber;
    private String address;
}
