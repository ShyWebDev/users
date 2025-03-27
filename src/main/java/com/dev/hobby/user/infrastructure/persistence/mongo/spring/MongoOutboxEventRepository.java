package com.dev.hobby.user.infrastructure.persistence.mongo.spring;

import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoOutboxEventRepository extends MongoRepository<OutboxEventDocument, String> {
    Optional<OutboxEventDocument> findByUniqueId(String uniqueId);
    List<OutboxEventDocument> findByStatus(String status);
}
