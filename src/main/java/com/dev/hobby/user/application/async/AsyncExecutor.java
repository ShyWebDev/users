package com.dev.hobby.user.application.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;
/**
 * 공통 비동기 실행 도우미 클래스.
 *
 * 내부에서 Executor를 이용해 CompletableFuture 기반의
 * 비동기 작업을 실행하고, 예외 처리를 공통화합니다.
 *
 * - 예: 내부 핸들러에서 병렬 처리하고자 할 때
 * - 예외는 공통적으로 로깅 및 fallback 처리 가능
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncExecutor {

    /**
     * 외부에서 주입받은 비동기 실행 스레드풀
     * (예: AsyncConfiguration 에서 등록)
     */
    private final Executor executor;

    /**
     * 비동기 작업을 실행하고, 예외 발생 시 로그 출력 후 null 반환.
     *
     * @param task 비동기 실행할 작업
     * @param <T> 반환 타입
     * @return CompletableFuture<T>
     */
    public <T> CompletableFuture<T> run(Supplier<T> task) {
        return CompletableFuture.supplyAsync(task, executor)
                .exceptionally(ex -> {
                    log.error("비동기 작업 중 예외 발생: {}", ex.getMessage(), ex);
                    return null;
                });
    }

    /**
     * 예외 처리 방식을 커스터마이징하고 싶은 경우 사용.
     *
     * @param task 실행할 작업
     * @param onError 예외 발생 시 실행할 fallback 함수
     * @param <T> 반환 타입
     * @return CompletableFuture<T>
     */
    public <T> CompletableFuture<T> run(Supplier<T> task, Function<Throwable, T> onError) {
        return CompletableFuture.supplyAsync(task, executor)
                .exceptionally(onError);
    }
}