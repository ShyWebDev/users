package com.dev.hobby.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.dev.hobby.user.external.persistence.mongo"
)
public class MongoConfig {

}
