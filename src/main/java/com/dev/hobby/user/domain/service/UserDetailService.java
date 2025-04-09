package com.dev.hobby.user.domain.service;

import com.dev.hobby.user.domain.model.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService {

    public UserDetail createUserDetailIfNeed(String userId, String nickname, String mobileNumber, String address) {
        if ( (userId != null && !userId.isBlank()) ||
                (nickname != null && !nickname.isBlank()) ||
                (mobileNumber != null && !mobileNumber.isBlank()) ||
                (address != null && !address.isBlank()) ) {
            return UserDetail.builder()
                    .userId(userId)
                    .nickname(nickname)
                    .mobileNumber(mobileNumber)
                    .address(address)
                    .build();
        }
        return null;
    }


}
