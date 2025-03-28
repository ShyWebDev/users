package com.dev.hobby.user.mapper.command;

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.api.dto.UserQueryResponse;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.external.persistence.mongo.entity.UserDocument;
import com.dev.hobby.user.external.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

/**
 * Domain → Entity 변환 전담
 * CQRS: Command 전용 저장 흐름
 * Clean Architecture: 도메인 계층 → 인프라 계층 방향만 의존
 * DDD: 도메인 객체가 외부 기술(MySQL Entity)에 직접 의존하지 않도록 분리
 */
@UtilityClass
public class UserDomainMapper {

    public static UserEntity toEntity(UserDomain domain) {
        return UserEntity.builder()
                .email(domain.getEmail())
                .name(domain.getName())
                // .userDetail(UserDetailEntity.builder()
                //     .address(domain.getUserDetail().getAddress())
                //     .phone(domain.getUserDetail().getPhone())
                //     .build())
                .build();
    }

    /*
    // Domain → Entity 변환
    public static UserDomain toUserDomain(String uniqueId, CreateUserCmd createUserCmd) {
        return UserDomain.builder()
                .uniqueId(uniqueId)
                .email(createUserCmd.getEmail())
                .password(createUserCmd.getPassword())
                .name(createUserCmd.getName())
                .build();
    }

    public static CreateUserResult toCreateUserResult(String uniqueId, CreateUserCmd createUserCmd) {
        return CreateUserResult.builder()
                .uniqueId(uniqueId)
                .callBackUrl(createUserCmd.getCallBackUrl())
                .build();
    }

    public static UserQueryResponse toResponse(UserDocument document) {
        return UserQueryResponse.builder()
                .uniqueId(document.getUniqueId())
                .email(document.getEmail())
                .name(document.getEmail())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }

     */

}