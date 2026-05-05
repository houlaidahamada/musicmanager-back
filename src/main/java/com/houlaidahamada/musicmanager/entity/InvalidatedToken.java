package com.houlaidahamada.musicmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "invalidated_tokens")
@Data
public class InvalidatedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "invalidated_at")
    private LocalDateTime invalidatedAt;

    @PrePersist
    protected void onCreate() {
        invalidatedAt = LocalDateTime.now();
    }
}