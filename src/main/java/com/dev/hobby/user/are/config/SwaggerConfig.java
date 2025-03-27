package com.dev.hobby.user.are.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * OpenAPI 문서 정보를 설정하는 Bean입니다.
     * - API의 기본 정보를 설정합니다. (Title, Description, Version)
     * - Swagger UI에서 기본적인 정보로 표시됩니다.
     *
     * @return OpenAPI 문서 정보 설정
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("User API")
                .description("사용자 관리 API")
                .version("1.0.0"));
    }
}