package com.dev.hobby.user.application.query.service;

import com.dev.hobby.outbox.application.query.command.GetOutboxCommand;
import com.dev.hobby.outbox.application.query.handler.GetOutboxHandler;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.user.application.query.command.GetUserCmd;
import com.dev.hobby.user.application.query.command.GetUserResult;
import com.dev.hobby.user.application.query.usecase.GetUserUseCase;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.domain.outbound.UserDetailQueryRepository;
import com.dev.hobby.user.domain.outbound.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserServiceImpl implements GetUserUseCase {

    private final UserQueryRepository userQueryRepository;
    private final UserDetailQueryRepository userDetailQueryRepository;
    private final GetOutboxHandler getOutboxHandler;

    @Override
    public GetUserResult getUser(GetUserCmd getUserCmd) {
        Optional<User> userOpt = userQueryRepository.findByUserId(getUserCmd.getUserId());
        if(userOpt.isPresent()){
            User user = userOpt.get();
            UserDetail userDetail = userDetailQueryRepository.findByUserId(user.getUserId()).orElse(null);

            return GetUserResult.builder()
                    .exists(true)
                    .user(user)
                    .userDetail(userDetail)
                    .build();
        }

        Outbox outbox = getOutboxHandler.getOutbox(GetOutboxCommand.builder()
                        .aggregateId(getUserCmd.getUserId())
                .build());
        return  GetUserResult.builder()
                .exists(false)
                .outbox(outbox)
                .build();
    }
}