package com.dev.hobby.user.application.query.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SyncUserCreateCmd {
    private String userId;
    private String email;
    private String password;
    private String name;
    private SyncUserDetailCreateCmd userDetail;
    private String callBackUrl;
}
