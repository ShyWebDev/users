package com.dev.hobby.user.application.query.service;

import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.application.mapper.UserDomainMapper;
import com.dev.hobby.user.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;

    //private final OutboxEventDocumentService outboxEventDocumentService;
    // private final ModelMapper modelMapper;


    public UserQueryResponse getUserByUniqueId(String uniqueId){
        return userQueryRepository.findByUniqueId(uniqueId)
                .map(UserDomainMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
        /*
        if(ObjectUtils.isEmpty(uniqueId))
            throw new CustomException("사용자 유일키는 필수값입니다.");

        Optional<UserDocument> userDocumentOpt = userQueryRepository.findByUniqueId(uniqueId);
        if(userDocumentOpt.isPresent())
            return modelMapper.map(userDocumentOpt.get(), UserQueryResponse.class);
        else{
            Optional<OutboxEventDocument> outboxEventDocumentOpt = outboxEventDocumentService.getOutBoxEventEntityByUniqueId(uniqueId);
            if(outboxEventDocumentOpt.isPresent()){
                if(OutBoxStatus.FAILED.toString().equals(outboxEventDocumentOpt.get().getStatus()))
                    throw new CustomException("사용자 정보 처리중 오류가 발생했습니다.");

                throw new CustomException("사용자 정보를 처리중입니다.");
            }
            else
                throw new CustomException("사용자 정보가 없습니다.");
        }
        */
    }
}
