package com.dev.hobby.user.external.persistence.command;

import com.dev.hobby.outbox.application.command.command.UpdateOutboxCommand;
import com.dev.hobby.user.domain.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveUserWithUpdateOutboxUseCase {

    private User user;
    private String outboxId;

}
