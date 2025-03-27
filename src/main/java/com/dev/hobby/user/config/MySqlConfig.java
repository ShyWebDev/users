package com.dev.hobby.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = {
            "com.dev.hobby.user.external.persistence.mysql",
            "com.dev.hobby.user.external.messaging.outbox"
})
public class MySqlConfig {
}
