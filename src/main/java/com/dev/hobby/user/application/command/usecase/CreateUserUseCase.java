package com.dev.hobby.user.application.command.usecase;

import com.dev.hobby.user.application.command.command.CreateUserCmd;
import com.dev.hobby.user.application.command.command.CreateUserResult;

public interface CreateUserUseCase {

    CreateUserResult createUser(CreateUserCmd createUserCmd);

}