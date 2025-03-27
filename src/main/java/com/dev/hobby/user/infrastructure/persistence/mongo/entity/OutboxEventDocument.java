package com.dev.hobby.user.infrastructure.persistence.mongo.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "outbox-events")
@Getter
@Builder
public class OutboxEventDocument {
    @Id
    private String _id;

    private String uniqueId;
    private String eventType;
    private Long eventOrderNo;
    private String payload;

    @Setter
    private String status;

    @Setter
    private Integer retryCount;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    private LocalDateTime syncedAt;
}
