package com.dev.hobby.user.outbound.persistence.mongo.spring;

import com.dev.hobby.user.outbound.persistence.mongo.entity.UserDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserDetailRepository extends MongoRepository<UserDetailDocument, String> {
    Optional<UserDetailDocument> findByUserId(String userId);
}
