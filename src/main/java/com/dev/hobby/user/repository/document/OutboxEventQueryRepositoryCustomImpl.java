package com.dev.hobby.user.repository.document;

import com.dev.hobby.user.entitys.document.OutboxEventDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OutboxEventQueryRepositoryCustomImpl implements OutboxEventQueryRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<OutboxEventDocument> findAllByStatusAndRetryCountGreaterThan(String status, Integer retryCount) {
        Query query = new Query(Criteria.where("status").is(status).and("retryCount").gt(retryCount));
        return mongoTemplate.find(query, OutboxEventDocument.class);
    }

    @Override
    public List<OutboxEventDocument> findAllByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status));
        return mongoTemplate.find(query, OutboxEventDocument.class);
    }

    @Override
    public Optional<OutboxEventDocument> findByUniqueId(String uniqueId) {
        Query query = new Query(Criteria.where("uniqueId").is(uniqueId));
        return mongoTemplate.find(query, OutboxEventDocument.class).stream().findFirst();
    }
}
