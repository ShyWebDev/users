package com.dev.hobby.user.repository.document;


import com.dev.hobby.user.entitys.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserQueryRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByUniqueId(String uniqueId);
}
