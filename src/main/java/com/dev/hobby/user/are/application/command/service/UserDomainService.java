package com.dev.hobby.user.are.application.command.service;

import com.dev.hobby.user.are.api.dto.UserPostRequest;
import com.dev.hobby.user.are.application.command.mapper.UserMapper;
import com.dev.hobby.user.are.domain.event.OutBoxStatus;
import com.dev.hobby.user.are.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.are.domain.model.UserDomain;
import com.dev.hobby.user.are.domain.repository.OutboxEventCmdRepository;
import com.dev.hobby.user.are.domain.repository.UserCmdRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.infrastructure.messaging.outbox.mapper.OutboxEventMapper;
import com.dev.hobby.user.are.common.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

/**
 * 사용자 도메인 서비스입니다.
 * 사용자 생성과 관련된 비즈니스 로직을 처리하고, 아웃박스 이벤트를 생성하여 발행합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {
    private final ObjectProvider<UserDomainService> self;  // 자기 자신을 호출할 ObjectProvider

    private final UserCmdRepository userCmdRepository;  // 사용자 데이터를 저장하는 리포지토리
    private final OutboxEventCmdRepository outboxEventCmdRepository; // 아웃박스 이벤트 데이터를 저장하는 리포지토리

    private final ObjectMapper objectMapper;  // 객체를 JSON으로 직렬화/역직렬화하기 위한 객체 맵퍼


    /**
     * 사용자를 생성하는 메서드입니다.
     * 이메일 중복 확인 후, 새로운 사용자 생성 로직을 실행합니다.
     *
     * @param request 사용자 생성 요청 DTO
     * @return 생성된 사용자 고유 ID
     */
    public String createUser(UserPostRequest request) {
        if(userCmdRepository.findByEmail(request.getEmail()).isPresent())
            throw new CustomException("이미 사용 중인 이메일입니다.");;

        String uniqueId = UUID.randomUUID().toString();
        try{
            // 아웃박스 이벤트 페이로드 직렬화
            String payload = objectMapper.writeValueAsString(request);  // 객체를 JSON 문자열로 직렬화

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

            // 비즈니스 로직:
            self.getIfAvailable().processUserCreate(uniqueId, request);
        }catch (Exception e){
            log.error("Error createUser Method: {}", e.getMessage(), e);
            throw new CustomException("사용자 생성 중 오류가 발생했습니다");
        }

        return uniqueId;
    }

    /**
     * 사용자 생성 비즈니스 로직을 처리합니다.
     * 비즈니스로직 처리(비밀번호 암호화), DTO → Domain 변환, 트랜잭션아웃박스 호출
     *
     * @param uniqueId 생성된 사용자 고유 ID
     * @param request 사용자 생성 요청 DTO
     */
    @Async  // 비동기적으로 실행되는 메서드
    public void processUserCreate(String uniqueId, UserPostRequest userPostRequest){
        try{
            // 비밀번호 암호화
            String encodedPassword = userPostRequest.getPassword();//passwordEncoder.encode(request.getPassword());

            // DTO → Domain 변환
            UserDomain userDomain = UserMapper.toDomain(uniqueId, userPostRequest);
            userDomain.setPassword(encodedPassword);

            self.getIfAvailable().saveEntity(userDomain);
        }catch (Exception e){
            log.error("Error processUserCreate Method: {}", e.getMessage(), e);
            self.getIfAvailable().outboxEventEntityFailure(uniqueId);
        }
    }

    /**
     * 사용자 엔티티 및 아웃박스 이벤트를 저장하는 메서드
     * @param userDomain 사용자 도메인 모델
     */
    @Transactional  // 트랜잭션을 보장하여 DB 저장과 이벤트 저장이 일관성 있게 처리됩니다.
    public void saveEntity(UserDomain userDomain){
        try{
           OutboxEventEntity outboxEventEntity = outboxEventCmdRepository.findByUniqueId(userDomain.getUniqueId()).orElse(null);
           if(ObjectUtils.isEmpty(outboxEventEntity))
               throw new CustomException("이벤트 정보가 없습니다.");

            outboxEventEntity.setStatus(OutBoxStatus.SUCCESS.toString());
            // 사용자 정보를 DB에 저장
            userCmdRepository.save(userDomain);

            // 아웃박스 이벤트를 DB에 저장
            outboxEventCmdRepository.save(OutboxEventMapper.toDomain(outboxEventEntity));
        }catch (Exception e){
            // 예외 발생 시 로깅
            log.error("Error saveEntity Method: {}", e.getMessage(), e);
        }
    }

    public void outboxEventEntityFailure(String uniqueId){
        OutboxEventEntity outboxEventEntity = outboxEventCmdRepository.findByUniqueId(uniqueId).orElse(null);
        if(ObjectUtils.isEmpty(outboxEventEntity))
            return;

        if(outboxEventEntity.getRetryCount() == 3){
            outboxEventEntity.setStatus(OutBoxStatus.FAILED.toString());
            outboxEventEntity.setSyncedAt(null);
            outboxEventCmdRepository.save(OutboxEventMapper.toDomain(outboxEventEntity));
            return;
        }
        outboxEventEntity.setStatus(OutBoxStatus.RECEIVED.toString());
        outboxEventEntity.setRetryCount(outboxEventEntity.getRetryCount() + 1);
        outboxEventEntity.setSyncedAt(null);
        outboxEventCmdRepository.save(OutboxEventMapper.toDomain(outboxEventEntity));
    }
}
