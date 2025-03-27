package com.dev.hobby.user.are.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.dev.hobby.user.infrastructure.persistence.mongo"
)
public class MongoConfig {

}
