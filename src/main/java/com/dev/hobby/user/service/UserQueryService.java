package com.dev.hobby.user.service;

import com.dev.hobby.user.domain.OutBoxStatus;
import com.dev.hobby.user.dto.UserResponse;
import com.dev.hobby.user.entitys.document.OutboxEventDocument;
import com.dev.hobby.user.entitys.document.UserDocument;
import com.dev.hobby.user.exceptions.UserException;
import com.dev.hobby.user.repository.document.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryService {
    private final OutboxEventDocumentService outboxEventDocumentService;
    private final UserQueryRepository userQueryRepository;

    private final ModelMapper modelMapper;


    public UserResponse getUserByUniqueId(String uniqueId){
        if(ObjectUtils.isEmpty(uniqueId))
            throw new UserException("사용자 유일키는 필수값입니다.");

        Optional<UserDocument> userDocumentOpt = userQueryRepository.findByUniqueId(uniqueId);
        if(userDocumentOpt.isPresent())
            return modelMapper.map(userDocumentOpt.get(), UserResponse.class);
        else{
            Optional<OutboxEventDocument> outboxEventDocumentOpt = outboxEventDocumentService.getOutBoxEventEntityByUniqueId(uniqueId);
            if(outboxEventDocumentOpt.isPresent()){
                if(OutBoxStatus.FAILED.toString().equals(outboxEventDocumentOpt.get().getStatus()))
                    throw new UserException("사용자 정보 처리중 오류가 발생했습니다.");

                throw new UserException("사용자 정보를 처리중입니다.");
            }
            else
                throw new UserException("사용자 정보가 없습니다.");
        }
    }
}
