package com.dev.hobby.user.infrastructure.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", schema = "user")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserEntity {

    @Id
    @Column(name = "unique_id", nullable = false, unique = true)
    private String uniqueId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

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
