package com.dev.hobby.user.infrastructure.persistence.mongo.adapter;

import com.dev.hobby.user.domain.repository.OutboxEventQueryRepository;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.spring.MongoOutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OutboxEventQueryRepositoryAdapter implements OutboxEventQueryRepository {

    private final MongoOutboxEventRepository mongoOutboxEventRepository;

    @Override
    public OutboxEventDocument save(OutboxEventDocument outboxEventDocument) {
        return mongoOutboxEventRepository.save(outboxEventDocument);
    }

    public Optional<OutboxEventDocument> findByUniqueId(String uniqueId) {
        return mongoOutboxEventRepository.findByUniqueId(uniqueId);
    }
}