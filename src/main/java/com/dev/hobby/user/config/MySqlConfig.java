package com.dev.hobby.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.dev.hobby.user.repository.entity"
)
public class MySqlConfig {
}
