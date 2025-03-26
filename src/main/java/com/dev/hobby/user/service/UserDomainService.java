package com.dev.hobby.user.service;

import com.dev.hobby.user.domain.UserDomain;
import com.dev.hobby.user.exceptions.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserDomainService {

    @Async
    public CompletableFuture<UserDomain> createUserDomain(String uniqueId, String email, String password){
        try {
            Thread.sleep(3000);
            //String test ="";
            //Integer.parseInt(test);

            return CompletableFuture.completedFuture(UserDomain.builder()
                    .uniqueId(uniqueId)
                    .email(email)
                    .password(password)
                    .build());
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
    }
}
