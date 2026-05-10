package com.mo.tradify.services.impl;

import com.mo.tradify.domain.entity.User;
import com.mo.tradify.domain.entity.WatchlistEntry;
import com.mo.tradify.repository.UserRepository;
import com.mo.tradify.repository.WatchlistRepository;
import com.mo.tradify.services.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;

    private User getUser(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }

    @Override
    public List<String> getSymbols(String username) {
        User user =  getUser(username);
        return watchlistRepository.findByUserId(user.getId())
            .stream()
            .map(WatchlistEntry::getSymbol)
            .toList();
    }

    @Override
    @Transactional
    public void addSymbol(String username, String symbol) {
        User user = getUser(username);
        if(watchlistRepository.existsByUserIdAndSymbol(user.getId(), symbol)) {
            return;
        }
        watchlistRepository.save(new WatchlistEntry(user, symbol));
    }

    @Override
    @Transactional
    public void removeSymbol(String username, String symbol) {
        User user = getUser(username);
        watchlistRepository.deleteByUserIdAndSymbol(user.getId(), symbol);
    }
}
