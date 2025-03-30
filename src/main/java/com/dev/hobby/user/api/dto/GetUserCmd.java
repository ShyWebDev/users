package com.dev.hobby.user.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserCmd {
    private String uniqueId;
}
