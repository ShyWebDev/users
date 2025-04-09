package com.dev.hobby.user.application.command.service;

import com.dev.hobby.outbox.application.command.command.CreateOutboxCommand;
import com.dev.hobby.outbox.application.command.handler.SaveOutboxHandler;
import com.dev.hobby.user.application.command.command.CreateUserCmd;
import com.dev.hobby.user.application.command.command.CreateUserResult;
import com.dev.hobby.user.application.command.event.UserCreateEvent;
import com.dev.hobby.user.application.command.usecase.CreateUserUseCase;
import com.dev.hobby.user.common.CustomException;
import com.dev.hobby.user.common.UliGenerate;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.domain.outbound.UserDuplicationChecker;
import com.dev.hobby.user.domain.service.UserDetailService;
import com.dev.hobby.user.domain.service.UserService;
import com.dev.hobby.user.outbound.persistence.command.SaveUserCmd;
import com.dev.hobby.user.outbound.persistence.mysql.executor.UserCommandExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 사용자 도메인 서비스입니다.
 * 사용자 생성과 관련된 비즈니스 로직을 처리하고, 아웃박스 이벤트를 생성하여 발행합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserServiceImpl implements CreateUserUseCase {
    private final UliGenerate uliGenerate;

    private final UserDuplicationChecker userDuplicationChecker;

    private final ObjectMapper objectMapper;

    private final SaveOutboxHandler saveOutboxHandler;

    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserCommandExecutor userCommandExecutor;

    /**
     * 사용자를 생성하는 메서드입니다.
     * 이메일 중복 확인 후, 새로운 사용자 생성 로직을 실행합니다.
     *
     * @param createUserCmd 사용자 생성 요청 DTO
     * @return 생성된 사용자 고유 ID
     */
    @Override
    public CreateUserResult createUser(CreateUserCmd createUserCmd) {
        if(userDuplicationChecker.isDuplicated(createUserCmd.getEmail()))
            throw new CustomException("이미 사용 중인 이메일입니다.");

        String userId = uliGenerate.generate();
        UserCreateEvent userCreateEvent = UserCreateEvent.builder()
                .userId(userId)
                .email(createUserCmd.getEmail())
                .password(createUserCmd.getPassword())
                .name(createUserCmd.getName())
                .nickname(createUserCmd.getUserDetail().getNickname())
                .mobileNumber(createUserCmd.getUserDetail().getMobileNumber())
                .address(createUserCmd.getUserDetail().getAddress())
                .callBackUrl(createUserCmd.getCallBackUrl())
                .build();

        String payload =  serializePayload(userCreateEvent);
        // 아웃박스 이벤트 엔티티 생성
        String outboxId = uliGenerate.generate();
        saveOutboxHandler.CreateHandler(CreateOutboxCommand
                .builder()
                .outboxId(outboxId)
                .aggregateType("USER")
                .aggregateId(userId)
                .eventType("CREATE_USER")
                .payload(payload)
                .build());

        processCreateUserAsync(userCreateEvent);

        return CreateUserResult.builder()
                .userId(userId)
                .callBackUrl(createUserCmd.getCallBackUrl())
                .build();
    }

    private String serializePayload(UserCreateEvent userCreateEvent) {
        try {
            return objectMapper.writeValueAsString(userCreateEvent);
        } catch (Exception e) {
            log.error("Error serializing payload: {}", e.getMessage(), e);
            throw new CustomException("이벤트 직렬화 실패");
        }
    }

    private void processCreateUserAsync(UserCreateEvent event){
        CompletableFuture.runAsync(() -> {
            try {
                saveOutboxHandler.processProcessingHandlerByAggregateId(event.getUserId());

                User user = userService
                        .createUser(event.getUserId(), event.getEmail(), event.getPassword(), event.getName());

                UserDetail userDetail = userDetailService
                        .createUserDetailIfNeed(event.getUserId(), event.getNickname(), event.getMobileNumber(), event.getAddress());

                userCommandExecutor.SaveUserAndDetailWithUpdateOutbox(SaveUserCmd.builder()
                        .user(user)
                        .userDetail(userDetail)
                        .build());

            }catch (Exception e){
                log.error("Error processing user creation: {}", e.getMessage(), e);
                throw new CustomException("사용자 생성 처리 실패");
            }
        });
    }
}
