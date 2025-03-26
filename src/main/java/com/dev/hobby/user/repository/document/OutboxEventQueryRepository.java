package com.dev.hobby.user.repository.document;

import com.dev.hobby.user.entitys.document.OutboxEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OutboxEventQueryRepository extends MongoRepository<OutboxEventDocument, String>, OutboxEventQueryRepositoryCustom {
}
