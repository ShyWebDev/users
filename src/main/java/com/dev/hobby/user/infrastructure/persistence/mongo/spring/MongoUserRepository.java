package com.dev.hobby.user.infrastructure.persistence.mongo.spring;

import com.dev.hobby.user.infrastructure.persistence.mongo.entity.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<UserDocument, String> {
}
