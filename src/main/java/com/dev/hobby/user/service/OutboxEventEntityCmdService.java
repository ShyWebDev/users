package com.dev.hobby.user.service;

import com.dev.hobby.user.domain.OutBoxStatus;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventCmdRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.OutboxEventEntity;
import com.dev.hobby.user.interfaces.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxEventEntityCmdService {

    private final ModelMapper modelMapper;
    private final OutboxEventCmdRepository otBoxEventCmdRepository;

    public void outboxEventEntityRetry(OutboxEventEntity outboxEventEntity){
        outboxEventEntity.setRetryCount(outboxEventEntity.getRetryCount() + 1);
        if(3 < outboxEventEntity.getRetryCount()){
            outboxEventEntity.setStatus(OutBoxStatus.FAILED.toString());
            outboxEventEntity.setSyncedAt(null);
            otBoxEventCmdRepository.save(outboxEventEntity);
            throw new CustomException("재시도 횟수 초과");
        }
        outboxEventEntity.setStatus(OutBoxStatus.RECEIVED.toString());
        outboxEventEntity.setSyncedAt(null);
        otBoxEventCmdRepository.save(outboxEventEntity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OutboxEventEntity save(OutboxEventEntity outboxEventEntity){
        return otBoxEventCmdRepository.save(outboxEventEntity);
    }
}
