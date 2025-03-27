package com.dev.hobby.user.infrastructure.persistence.mongo.spring;

import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoOutboxEventRepository extends MongoRepository<OutboxEventDocument, String> {
    List<OutboxEventDocument> findByStatus(String status);
}
