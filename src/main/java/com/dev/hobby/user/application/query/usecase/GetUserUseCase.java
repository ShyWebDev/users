package com.dev.hobby.user.application.query.usecase;

import com.dev.hobby.user.application.query.command.GetUserCmd;
import com.dev.hobby.user.application.query.command.GetUserResult;

public interface GetUserUseCase {

    GetUserResult getUser(GetUserCmd getUserCmd);

}