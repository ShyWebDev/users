package com.dev.hobby.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserCmdService {
    /*
    private final ObjectProvider<UserCmdService> self;

    private final UserCmdRepository userCmdRepository;
    private final OutboxEventCmdRepository outboxEventCmdRepository;

    private final UserDomainService userDomainService;

    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;
    private final OutboxEventMapper outboxEventMapper;

    private final UserDuplicationChecker userDuplicationChecker;

    @Async
    public CompletableFuture<UserCmdResponse> registerUserByUserRequest(UserPostRequest userPostRequest){
        try{
            if(ObjectUtils.isEmpty(userPostRequest))
                return null;

            if(ObjectUtils.isEmpty(userPostRequest))
                throw new CustomException("입력내용이 없습니다.");

            if(ObjectUtils.isEmpty(userPostRequest.getUserName()))
                throw new CustomException("사용자 이름은 필수값입니다.");

            if(ObjectUtils.isEmpty(userPostRequest.getEmail()))
                throw new CustomException("이메일은 필수값입니다.");

            if(ObjectUtils.isEmpty(userPostRequest.getPassword()))
                throw new CustomException("비밀번호는 필수값입니다.");

            String emailRegex  ="^(?!\\.)(?!.*\\.\\.)([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$";
            if(!userPostRequest.getEmail().matches(emailRegex))
                throw new CustomException("이메일 형식이 올바르지 않습니다.");

            String uniqueId = UUID.randomUUID().toString();

            UserDomain.valid(userPostRequest.getEmail(), userPostRequest.getPassword(), userDuplicationChecker);

            String payload = objectMapper.writeValueAsString(userPostRequest);
            OutBoxEventDomain outBoxEventDomain = OutBoxEventDomain.createOutboxDomain(
                    uniqueId
                    ,   "registerUserByUserRequest"
                    ,   payload);

            OutboxEventEntity outBoxEventEntity = outboxEventMapper.toEntity(outBoxEventDomain);
            outBoxEventEntity.setStatus(OutBoxStatus.RECEIVED.toString());
            outboxEventCmdRepository.save(outBoxEventEntity);

            UserCmdResponse userCmdResponse = new UserCmdResponse();
            userCmdResponse.setUniqueId(uniqueId);
            userCmdResponse.setCallBackUrl(userPostRequest.getCallBackUrl());

            CompletableFuture.runAsync(() -> {
               userDomainService.createUserDomain(uniqueId, userPostRequest.getEmail(), userPostRequest.getPassword())
                   .thenAccept(userDomain -> {
                       try{
                           // 도메인 비즈니스로직
                           UserEntity userEntity = null;//userMapper.toEntity(userDomain);
                           self.getIfAvailable().saveEntityAsync(userEntity);
                       }catch (Exception e){
                           log.error("registerUserByUserRequest saveEntityWithOutbox error", e);
                           throw new CustomException("사용자 서비스 오류입니다.");
                       }
                   });
            });

            return CompletableFuture.completedFuture(userCmdResponse);
        }catch (Exception e){
            log.error("registerUserByUserRequest", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Void> registerUserByOutboxEventDomain(OutBoxEventDomain outBoxEventDomain){
        try{
            OutboxEventEntity outBoxEventEntity = outboxEventMapper.toEntity(outBoxEventDomain);
            UserPostRequest userPostRequest = objectMapper.readValue(outBoxEventEntity.getPayload(), UserPostRequest.class);

            return userDomainService.createUserDomain(outBoxEventDomain.getUniqueId(), userPostRequest.getEmail(), userPostRequest.getPassword())
            .thenCompose(userDomain -> {
                try{
                    // 도메인 비즈니스로직
                    UserEntity userEntity = userMapper.toEntity(userDomain);
                    return self.getIfAvailable().saveEntityAsync(userEntity);
                }catch (Exception e){
                    log.error("registerUserByUserRequest saveEntityWithOutbox error", e);
                    throw new CustomException("사용자 서비스 오류입니다.");
                }
            }).exceptionally(ex->{
                log.error("registerUserByOutboxEventDomain", ex);
                self.getIfAvailable().outboxEventEntityFailure(outBoxEventDomain.getUniqueId());
                return null;
            });
        }catch (Exception e){
            log.error("registerUserByUserRequest", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Void> saveEntityAsync(UserEntity userEntity){
        try{
            OutboxEventEntity outboxEventEntity = outboxEventCmdRepository.findById(userEntity.getUniqueId()).orElse(null);
            if(ObjectUtils.isEmpty(outboxEventEntity))
                return CompletableFuture.completedFuture(null);

            userCmdRepository.save(userEntity);

            outboxEventEntity.setStatus(OutBoxStatus.SUCCESS.toString());
            outboxEventEntity.setSyncedAt(null);
            outboxEventCmdRepository.save(outboxEventEntity);

            String test ="";
            Integer.parseInt(test);

            return CompletableFuture.completedFuture(null);
        }catch (Exception e){
            return CompletableFuture.failedFuture(e);
        }
    }

    public void outboxEventEntityFailure(String uniqueId){
        OutboxEventEntity outboxEventEntity = outboxEventCmdRepository.findById(uniqueId).orElse(null);
        if(ObjectUtils.isEmpty(outboxEventEntity))
            return;

        if(outboxEventEntity.getRetryCount() == 3){
            outboxEventEntity.setStatus(OutBoxStatus.FAILED.toString());
            outboxEventEntity.setSyncedAt(null);
            outboxEventCmdRepository.save(outboxEventEntity);
            return;
        }
        outboxEventEntity.setStatus(OutBoxStatus.RECEIVED.toString());
        outboxEventEntity.setRetryCount(outboxEventEntity.getRetryCount() + 1);
        outboxEventEntity.setSyncedAt(null);
        outboxEventCmdRepository.save(outboxEventEntity);
    }

     */
}
