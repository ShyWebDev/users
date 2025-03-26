package com.dev.hobby.user.infrastructure.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 비동기 작업을 위한 스프링 설정 클래스입니다.
 *
 * - @EnableAsync: Spring의 비동기 실행 기능을 활성화합니다.
 * - Executor Bean을 정의하여 @Async 또는 AsyncExecutor에서 사용할 수 있도록 합니다.
 * - 기본적으로 고정된 크기의 스레드 풀(ThreadPool)을 사용합니다.
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {

    /**
     * 비동기 실행을 위한 Executor Bean 등록
     *
     * 이 Executor는 스프링 컨테이너에 등록되며,
     * AsyncExecutor 또는 @Async 어노테이션 기반 비동기 작업에서 사용됩니다.
     *
     * 필요한 경우 ThreadPoolTaskExecutor 등으로 확장 가능합니다.
     *
     * @return Executor 구현체
     */
    @Bean
    public Executor asyncExecutor() {
        // 단순한 고정 크기 스레드 풀 사용 (예: 최대 10개 동시 실행)
        return Executors.newFixedThreadPool(10);
    }
}