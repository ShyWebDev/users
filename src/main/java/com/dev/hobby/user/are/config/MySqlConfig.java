package com.dev.hobby.user.are.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = {
        "com.dev.hobby.user.infrastructure.persistence.mysql",
        "com.dev.hobby.user.infrastructure.messaging.outbox"
})
public class MySqlConfig {
}
