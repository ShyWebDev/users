package com.dev.hobby.user.external.persistence.event;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.user.domain.model.UserDomain;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveUserOutboxEvent {

    private UserDomain userDomain;
    private Outbox outbox;

}
