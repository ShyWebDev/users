package com.dev.hobby.user.application.query.service;

import com.dev.hobby.user.application.query.command.SyncUserCreateCmd;
import com.dev.hobby.user.application.query.usecase.SyncUserUseCase;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.domain.service.UserDetailService;
import com.dev.hobby.user.domain.service.UserService;
import com.dev.hobby.user.outbound.persistence.command.SaveUserCmd;
import com.dev.hobby.user.outbound.persistence.mongo.executor.UserQueryExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SyncUserServiceImpl implements SyncUserUseCase {

    private final UserService userService;
    private final UserDetailService userDetailService;

    private final UserQueryExecutor userQueryExecutor;


    @Override
    public void syncUser(SyncUserCreateCmd syncUserCreateCmd) {
        User user = userService
                .createUser(syncUserCreateCmd.getUserId(), syncUserCreateCmd.getEmail(), syncUserCreateCmd.getPassword(), syncUserCreateCmd.getName());

        UserDetail userDetail = null;
        if( syncUserCreateCmd.getUserDetail() != null) {
            userDetail = userDetailService
                    .createUserDetailIfNeed(syncUserCreateCmd.getUserId(), syncUserCreateCmd.getUserDetail().getNickname(), syncUserCreateCmd.getUserDetail().getMobileNumber(), syncUserCreateCmd.getUserDetail().getAddress());
        }

        userQueryExecutor.saveUserAndDetail(SaveUserCmd.builder()
                .user(user)
                .userDetail(userDetail)
                .build());
    }
}