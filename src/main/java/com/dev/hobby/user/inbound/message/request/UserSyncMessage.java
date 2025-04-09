package com.dev.hobby.user.inbound.message.request;

public record UserSyncMessage(
    String userId,
    String email,
    String password,
    String name,

    String nickname,
    String mobileNumber,
    String address
){}