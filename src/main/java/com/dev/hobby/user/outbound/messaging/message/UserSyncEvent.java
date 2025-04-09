package com.dev.hobby.user.outbound.messaging.message;

public record UserSyncEvent(
    String userId,
    String email,
    String password,
    String name,

    String nickname,
    String mobileNumber,
    String address
){}