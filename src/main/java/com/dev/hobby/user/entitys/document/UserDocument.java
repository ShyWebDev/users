package com.dev.hobby.user.entitys.document;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Getter
@Builder
public class UserDocument {
    @Id
    private String _id;

    private String uniqueId;

    @Setter
    private String email;

    @Setter
    private String password;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;
}
