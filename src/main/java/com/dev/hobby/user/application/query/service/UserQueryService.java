package com.dev.hobby.user.application.query.service;

import com.dev.hobby.user.api.dto.GetUserCmd;
import com.dev.hobby.user.api.dto.GetUserResult;
import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
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

        Optional<UserDomain> queryDomainOpt = userQueryRepository.findByUniqueId(getUserCmd.getUniqueId());
        if(queryDomainOpt.isPresent())
            return UserDomainMapper.toGetUserResult(queryDomainOpt.get());

        throw new CustomException("User not found with uniqueId: "+getUserCmd.getUniqueId());
    }
}
