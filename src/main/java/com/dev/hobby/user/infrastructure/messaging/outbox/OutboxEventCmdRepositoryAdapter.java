package com.dev.hobby.user.infrastructure.messaging.outbox;

import com.dev.hobby.user.domain.model.OutBoxEventDomain;
import com.dev.hobby.user.domain.repository.OutboxEventCmdRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.jpa.JpaOutboxEventRepository;
import com.dev.hobby.user.infrastructure.messaging.outbox.mapper.OutboxEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OutboxEventCmdRepositoryAdapter implements OutboxEventCmdRepository {

    private final JpaOutboxEventRepository jpaOutboxEventRepository;

    @Override
    public Optional<OutboxEventEntity> findByUniqueId(String uniqueId) {
        return jpaOutboxEventRepository.findByUniqueId(uniqueId);
    }

    @Override
    public List<OutboxEventEntity> findTop50BySyncedAtIsNullOrderByCreatedAt() {
        return jpaOutboxEventRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
    }

    @Override
    public OutboxEventEntity save(OutboxEventEntity outboxEventEntity) {
        return jpaOutboxEventRepository.save(outboxEventEntity);
    }
}