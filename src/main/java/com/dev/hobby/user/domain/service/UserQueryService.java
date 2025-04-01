package com.dev.hobby.user.domain.service;

import com.dev.hobby.user.application.query.command.GetUserCmd;
import com.dev.hobby.user.application.query.command.GetUserResult;
import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.outbound.UserQueryRepository;
import com.dev.hobby.user.mapper.domain.UserDomainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;
    public GetUserResult getUser(GetUserCmd getUserCmd){

        Optional<User> queryDomainOpt = userQueryRepository.findByUserId(getUserCmd.getUserId());
        if(queryDomainOpt.isPresent())
            return UserDomainMapper.toGetUserResult(queryDomainOpt.get());

        throw new CustomException("User not found with uniqueId: "+getUserCmd.getUserId());
    }
}
