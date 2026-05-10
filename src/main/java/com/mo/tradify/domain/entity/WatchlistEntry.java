package com.mo.tradify.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "watchlist", schema = "app")
@Getter
@NoArgsConstructor
public class WatchlistEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String symbol;

    @Column(name = "subscribed_at",nullable = false, updatable = false)
    private Instant subscribedAt;

    public WatchlistEntry(User user, String symbol) {
        this.user = user;
        this.symbol = symbol;
        this.subscribedAt = Instant.now();
    }
}
