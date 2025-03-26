package com.dev.hobby.user.infrastructure.messaging.outbox;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox-events", schema = "user")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class OutboxEventEntity {

    @Id
    @Column(name = "unique_id", nullable = false, unique = true)
    private String uniqueId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "event_order_no")
    private Long eventOrderNo;

    @Column(name = "payload", nullable = false)
    private String payload;

    @Setter
    @Column(name = "status", nullable = false)
    private String status;

    @Setter
    @Column(name = "retry_count", nullable = false)
    private Integer retryCount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Setter
    @Column(name = "synced_at")
    private LocalDateTime syncedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();

        if(ObjectUtils.isEmpty(this.createdAt))
            this.createdAt = LocalDateTime.now();
    }
}
