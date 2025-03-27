package com.dev.hobby.user.external.persistence.mongo.spring;

import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByUniqueId(String uniqueId);
}
