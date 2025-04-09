package com.dev.hobby.user.outbound.persistence.mongo.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_datail")
@Getter
@Builder
public class UserDetailDocument {
    @Id
    private String _id;

    private String userId;

    @Setter
    private String nickname;

    @Setter
    private String mobileNumber;

    @Setter
    private String address;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;
}
