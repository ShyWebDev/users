package com.dev.hobby.user.domain.service;

import com.dev.hobby.outbox.application.command.command.CreateOutboxCommand;
import com.dev.hobby.outbox.application.command.command.UpdateOutboxCommand;
import com.dev.hobby.outbox.application.command.handler.SaveOutboxHandler;
import com.dev.hobby.user.application.command.command.CreateUserCmd;
import com.dev.hobby.user.application.command.command.CreateUserResult;
import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.common.UliGenerate;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.outbound.UserDuplicationChecker;
import com.dev.hobby.user.external.persistence.command.SaveUserWithUpdateOutboxUseCase;
import com.dev.hobby.user.external.persistence.service.UserExternalService;
import com.dev.hobby.user.mapper.domain.UserDomainMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

/**
 * 사용자 도메인 서비스입니다.
 * 사용자 생성과 관련된 비즈니스 로직을 처리하고, 아웃박스 이벤트를 생성하여 발행합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {
    private final UliGenerate uliGenerate;

    private final UserDuplicationChecker UserDuplicationChecker;

    private final ObjectMapper objectMapper;  // 객체를 JSON으로 직렬화/역직렬화하기 위한 객체 맵퍼

    private final SaveOutboxHandler saveOutboxHandler;  // 아웃박스 이벤트 생성 핸들러


    private final UserExternalService userExternalService;

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

        String outboxId = uliGenerate.generate();  // 사용자 고유 ID 생성
        String userId = uliGenerate.generate();  // 사용자 고유 ID 생성
        // 아웃박스 이벤트 엔티티 생성

        saveOutboxHandler.CreateHandler(CreateOutboxCommand
                .builder()
                .outboxId(outboxId)
                .aggregateType("USER")
                .aggregateId(userId)
                .eventType("CREATE_USER")
                .build());

        return completableFutureProcessCreateUserAndSave(createUserCmd, userId, outboxId);
    }

    private CreateUserResult completableFutureProcessCreateUserAndSave(CreateUserCmd createUserCmd, String userId, String outboxId){
        CompletableFuture.runAsync(() -> {
            User user = UserDomainMapper.toUserDomain(userId, createUserCmd);

            // 비밀번호 암호화 등 복잡하고 오래걸리는 작업들 실행
            String encodedPassword = createUserCmd.getPassword();//passwordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);

            userExternalService.saveUserAndUpdateOutbox(SaveUserWithUpdateOutboxUseCase.builder()
                    .user(user)
                    .outboxId(outboxId)
                    .build());
        });
        return UserDomainMapper.toCreateUserResult(userId, createUserCmd);
    }
}
