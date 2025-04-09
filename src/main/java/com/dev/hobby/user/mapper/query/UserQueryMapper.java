package com.dev.hobby.user.mapper.query;

import com.dev.hobby.user.application.query.command.SyncUserCreateCmd;
import com.dev.hobby.user.application.query.command.SyncUserDetailCreateCmd;
import com.dev.hobby.user.inbound.message.request.UserSyncMessage;
import lombok.experimental.UtilityClass;

/**
 * Document → DTO 변환 전담
 * CQRS: Query 전용 응답 Mapper
 * Clean Architecture: Infra 계층 Document → API 계층 DTO
 */
@UtilityClass
public class UserQueryMapper {

    public SyncUserCreateCmd toSyncUserCreateCmd(UserSyncMessage msg) {
        SyncUserDetailCreateCmd userDetailCreateCmd = null;

        if(msg.nickname() != null || msg.mobileNumber() != null || msg.address() != null) {
            userDetailCreateCmd = SyncUserDetailCreateCmd.builder()
                    .nickname(msg.nickname())
                    .mobileNumber(msg.mobileNumber())
                    .address(msg.address())
                    .build();
        }

        return SyncUserCreateCmd.builder()
                .userId(msg.userId())
                .email(msg.email())
                .password(msg.password())
                .name(msg.name())
                .userDetail(userDetailCreateCmd)
                .build();
    }


}