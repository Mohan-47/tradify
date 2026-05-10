package com.mo.tradify.repository;

import com.mo.tradify.domain.entity.WatchlistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WatchlistRepository extends JpaRepository<WatchlistEntry, UUID> {
    List<WatchlistEntry> findByUserId(UUID userId);
    Optional<WatchlistEntry> findByUserIdAndSymbol(UUID userId, String symbol);
    boolean existsByUserIdAndSymbol(UUID userId, String symbol);

    @Transactional
    void deleteByUserIdAndSymbol(UUID userId, String symbol);

}
