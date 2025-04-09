package com.dev.hobby.user.outbound.persistence.command;

import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveUserCmd {

    private User user;
    private UserDetail userDetail;
}
