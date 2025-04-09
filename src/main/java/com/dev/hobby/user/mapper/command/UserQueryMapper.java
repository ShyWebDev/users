package com.dev.hobby.user.mapper.command;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.user.application.query.command.GetUserCmd;
import com.dev.hobby.user.application.query.command.GetUserResult;
import com.dev.hobby.user.domain.model.User;
import com.dev.hobby.user.domain.model.UserDetail;
import com.dev.hobby.user.inbound.api.response.UserQueryResponse;
import lombok.experimental.UtilityClass;

/**
 * DTO → Command 변환 전용
 * CQRS: Command 흐름 전용 Mapper
 * Clean Architecture: API 계층 DTO → 도메인 계층 모델로 흐름
 * DDD: Domain 객체는 Builder를 통해 명확한 생성 책임 보장
 */
@UtilityClass
public class UserQueryMapper {



    public static GetUserCmd toGetUserCmd(String userId) {
        return GetUserCmd.builder()
                .userId(userId)
                .build();
    }

    public static UserQueryResponse toUserQueryResponse(GetUserResult getUserResult) {
        if(getUserResult.isExists()){
            User user = getUserResult.getUser();
            UserDetail userDetail = getUserResult.getUserDetail();

            return new UserQueryResponse(
                    user.getUserId(),
                    user.getEmail(),
                    user.getName(),
                    userDetail.getNickname(),
                    userDetail.getMobileNumber(),
                    userDetail.getAddress(),
                    null,
                    null,
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );
        }

        Outbox outbox = getUserResult.getOutbox();

        return new UserQueryResponse(
                outbox.getAggregateId(),
                null,
                null,
                null,
                null,
                null,
                outbox.getCallbackUrl(),
                outbox.getStatus().name(),
                outbox.getCreatedAt(),
                outbox.getUpdatedAt()
        );
    }


}