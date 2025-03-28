package com.dev.hobby.user.application.command.service;

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.mapper.command.UserDomainMapper;
import com.dev.hobby.user.mapper.infra.UserDomainEventMapper;
import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.domain.event.OutBoxStatus;
import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.domain.repository.OutboxEventCmdRepository;
import com.dev.hobby.user.domain.repository.UserCmdRepository;
import com.dev.hobby.user.domain.service.UserDuplicationChecker;
import com.dev.hobby.user.external.messaging.publisher.UserKafkaPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * 사용자 도메인 서비스입니다.
 * 사용자 생성과 관련된 비즈니스 로직을 처리하고, 아웃박스 이벤트를 생성하여 발행합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {
    private final UserCmdRepository userCmdRepository;  // 사용자 데이터를 저장하는 리포지토리
    private final UserDuplicationChecker UserDuplicationChecker;
    private final OutboxEventCmdRepository outboxEventCmdRepository; // 아웃박스 이벤트 데이터를 저장하는 리포지토리

    private final UserKafkaPublisher userKafkaPublisher;  // 사용자 이벤트를 발행하는 퍼블리셔
    private final ObjectMapper objectMapper;  // 객체를 JSON으로 직렬화/역직렬화하기 위한 객체 맵퍼


    /**
     * 사용자를 생성하는 메서드입니다.
     * 이메일 중복 확인 후, 새로운 사용자 생성 로직을 실행합니다.
     *
     * @param createUserCmd 사용자 생성 요청 DTO
     * @return 생성된 사용자 고유 ID
     */
    @Transactional
    public CreateUserResult createUser(CreateUserCmd createUserCmd) {
        if(UserDuplicationChecker.isDuplicated(createUserCmd.getEmail()))
            throw new CustomException("이미 사용 중인 이메일입니다.");

        String payload;
        try{
            // 아웃박스 이벤트 페이로드 직렬화
            payload = objectMapper.writeValueAsString(createUserCmd);  // 객체를 JSON 문자열로 직렬화
        }catch (Exception e){
            log.error("Error createUser Method: {}", e.getMessage(), e);
            throw new CustomException("사용자 생성 중 오류가 발생했습니다");
        }

        String uniqueId = UUID.randomUUID().toString();
        // 아웃박스 이벤트 엔티티 생성
        OutBoxEventDomain outBoxEventDomain = OutBoxEventDomain.builder()
                .uniqueId(uniqueId)  // 사용자 고유 ID
                .eventType("USER_CREATED")  // 이벤트 타입 (예: 사용자 생성)
                .eventOrderNo(0L)  // 이벤트 순서 (필요 시 관리)
                .payload(payload)  // 이벤트 데이터 (직렬화된 사용자 정보)
                .status(OutBoxStatus.RECEIVED.toString())  // 이벤트 처리 상태
                .retryCount(0)  // 재시도 횟수
                .build();
        outboxEventCmdRepository.save(outBoxEventDomain);

        return completableFutureProcessCreateUserAndSave(createUserCmd, uniqueId, outBoxEventDomain);
    }

    public CreateUserResult completableFutureProcessCreateUserAndSave(CreateUserCmd createUserCmd, String uniqueId, OutBoxEventDomain outBoxEventDomain){
        UserDomain userDomain = UserDomainMapper.toUserDomain(uniqueId, createUserCmd);

        CompletableFuture.runAsync(() -> {
            try{
                Thread.sleep(10000);
            }catch (Exception e){

            }
            // 비밀번호 암호화 등 복잡하고 오래걸리는 작업들 실행
            String encodedPassword = createUserCmd.getPassword();//passwordEncoder.encode(request.getPassword());

            userDomain.setPassword(encodedPassword);
            userCmdRepository.save(userDomain);

            outBoxEventDomain.setStatus(OutBoxStatus.SUCCESS.toString());
            // 아웃박스 이벤트를 DB에 저장
            outboxEventCmdRepository.save(outBoxEventDomain);

            userKafkaPublisher.publishUserCreatedEvent(UserDomainEventMapper.toUserCreatedEvent(userDomain));
        });
        return UserDomainMapper.toCreateUserResult(uniqueId, createUserCmd);
    }
}
