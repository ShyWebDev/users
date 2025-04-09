package com.dev.hobby.user.mapper.infra;

import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.outbound.persistence.mongo.entity.UserDetailDocument;
import lombok.experimental.UtilityClass;

/**
 * Domain → Kafka Event Object 변환
 * Event-Driven Architecture + DDD: 도메인 이벤트 → 외부 메시지 변환
 * Clean Architecture: 도메인은 메시징 시스템에 직접 의존하지 않음
 */
@UtilityClass
public class UserDetailEventMapper {

    public static UserDetailDocument toDocument(UserDetail domain) {
        return UserDetailDocument.builder()
                .userId(domain.getUserId())
                .nickname(domain.getNickname())
                .mobileNumber(domain.getMobileNumber())
                .address(domain.getAddress())
                .build();
    }

    public static UserDetail toDomain(UserDetailDocument document) {
        return UserDetail.builder()
                .userId(document.getUserId())
                .nickname(document.getNickname())
                .mobileNumber(document.getMobileNumber())
                .address(document.getAddress())
                .build();
    }




}