package com.dev.hobby.user.application.mapper;

/**
 * DTO와 도메인 객체(User) 간의 변환을 담당하는 매퍼 클래스입니다.
 *
 * - MapStruct를 사용하여 컴파일 시 자동으로 매핑 구현
 * - @Mapper 어노테이션을 사용하여 자동 매핑 코드 생성
 */

import com.dev.hobby.user.api.dto.CreateUserCmd;
import com.dev.hobby.user.api.dto.CreateUserResult;
import com.dev.hobby.user.api.dto.UserCmdRequest;
import com.dev.hobby.user.api.dto.UserCmdResponse;
import com.dev.hobby.user.domain.model.UserDomain;
import com.dev.hobby.user.infrastructure.persistence.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    // DTO -> COMMAND 변환
    public static CreateUserCmd byUserCmdRequest(UserCmdRequest userCmdRequest) {
        return CreateUserCmd.builder()
                .email(userCmdRequest.getEmail())
                .password(userCmdRequest.getPassword())
                .name(userCmdRequest.getName())
                .build();
    }

    // COMMAND → DTO 변환
    public static UserCmdResponse byCreateUserResult(CreateUserResult createUserResult) {
        return UserCmdResponse.builder()
                .uniqueId(createUserResult.getUniqueId())
                .callBackUrl(createUserResult.getCallBackUrl())
                .build();
    }


    // COMMAND → DOMAIN 변환
    public static UserDomain byCreateUserCmd(String uniqueId, CreateUserCmd createUserCmd) {
        return UserDomain.builder()
                .uniqueId(uniqueId)
                .email(createUserCmd.getEmail())
                .password(createUserCmd.getPassword())
                .name(createUserCmd.getName())
                .build();
    }

    // COMMAND → DTO 변환
    public static CreateUserResult byCreateUserCmd2(String uniqueId, CreateUserCmd createUserCmd) {
        return CreateUserResult.builder()
                .uniqueId(uniqueId)
                .callBackUrl(createUserCmd.getCallBackUrl())
                .build();
    }

    // Domain → Entity 변환
    public static UserEntity byUserDomain(UserDomain userDomain) {
        return UserEntity.builder()
                .uniqueId(userDomain.getUniqueId())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .name(userDomain.getName())
                .createdAt(userDomain.getCreatedAt())
                .build();
    }


}