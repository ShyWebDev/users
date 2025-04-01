package com.dev.hobby.user.application.query.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserCmd {
    private String userId;
}
