package com.dev.hobby.user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = {
            "com.dev.hobby.user.external.persistence.mysql.jpa",
})
@EntityScan(basePackages = "com.dev.hobby.user.external.persistence.mysql.entity")
public class MySqlConfig {
}
