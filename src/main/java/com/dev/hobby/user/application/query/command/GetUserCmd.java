package com.dev.hobby.user.application.query.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserCmd {
    private String userId;
}
