package com.mo.tradify.controller;

import com.mo.tradify.services.StockSubscriptionService;
import com.mo.tradify.services.WatchlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@Slf4j
@RequiredArgsConstructor
public class WatchlistController {
    private final WatchlistService watchlistService;
    private final StockSubscriptionService stockSubscriptionService;
    private static final String DEFAULT_USER = "default";

    @GetMapping
    public List<String> getWatchlist(){
        return watchlistService.getSymbols(DEFAULT_USER);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSymbol(@RequestBody String symbol){
        String cleaned = symbol.trim();
        watchlistService.addSymbol(DEFAULT_USER, cleaned);
        stockSubscriptionService.subscribeToSymbol(cleaned);
    }

    @DeleteMapping("/{symbol}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSymbol(@PathVariable String symbol){
        String cleaned = symbol.trim();
        watchlistService.removeSymbol(DEFAULT_USER, cleaned);
        stockSubscriptionService.unsubscribeFromSymbol(cleaned);
    }
}
