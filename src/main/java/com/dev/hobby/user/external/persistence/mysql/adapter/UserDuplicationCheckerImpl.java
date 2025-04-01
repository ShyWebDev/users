package com.dev.hobby.user.external.persistence.mysql.adapter;

import com.dev.hobby.user.domain.outbound.UserCmdRepository;
import com.dev.hobby.user.domain.outbound.UserDuplicationChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDuplicationCheckerImpl implements UserDuplicationChecker {

    private final UserCmdRepository userCmdRepository;

    @Override
    public boolean isDuplicated(String email) {
        return userCmdRepository.findByEmail(email).isPresent() ;
    }
}
