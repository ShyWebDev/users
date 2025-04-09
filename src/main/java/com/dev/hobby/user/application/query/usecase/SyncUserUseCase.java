package com.dev.hobby.user.application.query.usecase;

import com.dev.hobby.user.application.query.command.SyncUserCreateCmd;

public interface SyncUserUseCase {

    void syncUser(SyncUserCreateCmd syncUserCreateCmd);

}