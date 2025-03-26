package com.dev.hobby.user.infrastructure.persistence.mongo.adapter;

import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.infrastructure.persistence.mongo.spring.MongoOutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OutboxEventQueryRepositoryAdapter{

    private final MongoOutboxEventRepository mongoOutboxEventRepository;

    public List<OutboxEventDocument> findAll() {
        return mongoOutboxEventRepository.findAll();
    }

    public List<OutboxEventDocument> findByAggregateType(String type) {
        return mongoOutboxEventRepository.findByAggregateType(type);
    }
}