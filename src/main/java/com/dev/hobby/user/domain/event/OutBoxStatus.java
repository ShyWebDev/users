package com.dev.hobby.user.domain.event;

/**
 * ChunkStatus는 파일 청크의 상태를 나타내는 열거형(enum)입니다.
 * <p>
 * 청크 업로드 프로세스 동안 발생할 수 있는 상태를 정의합니다.
 * 예를 들어, UPLOADING, SUCCESS, FAILED 등이 있습니다.
 * </p>
 *
 * @author 
 * @version 1.0
 */
public enum OutBoxStatus {
    RECEIVED,
    PROCESSING,
    PUBLISHING,
    PUBLISHED,
    FAILED,
    SUCCESS;

}
