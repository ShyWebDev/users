package com.dev.hobby.user.application.query.service;

import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.application.mapper.UserQueryMapper;
import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.domain.event.OutBoxStatus;
import com.dev.hobby.user.domain.repository.OutboxEventQueryRepository;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
import com.dev.hobby.user.external.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;
    private final OutboxEventQueryRepository outboxEventQueryRepository;
    public UserQueryResponse getUserByUniqueId(String uniqueId){

        Optional<UserDocument> userDocumentOpt = userQueryRepository.findByUniqueId(uniqueId);
        if(userDocumentOpt.isPresent())
            return UserQueryMapper.toResponse(userDocumentOpt.get());
        else{
            Optional<OutboxEventDocument> outboxEventDocumentOpt = outboxEventQueryRepository.findByUniqueId(uniqueId);
            if(outboxEventDocumentOpt.isPresent()){
                if(OutBoxStatus.FAILED.toString().equals(outboxEventDocumentOpt.get().getStatus()))
                    throw new CustomException("사용자 정보 처리중 오류가 발생했습니다.");

                throw new CustomException("사용자 정보를 처리중입니다.");
            }
            else
                throw new CustomException("사용자 정보가 없습니다.");
        }
    }
}
