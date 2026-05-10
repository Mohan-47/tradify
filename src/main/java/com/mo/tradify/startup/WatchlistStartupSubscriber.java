package com.mo.tradify.startup;

import com.mo.tradify.events.FinnhubConnectionEvent;
import com.mo.tradify.services.StockSubscriptionService;
import com.mo.tradify.services.WatchlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class WatchlistStartupSubscriber{

    private final WatchlistService watchlistService;
    private final StockSubscriptionService stockSubscriptionService;

    @EventListener
    public void onFinnhubConnection(FinnhubConnectionEvent event) {
        List<String> symbols = watchlistService.getSymbols("default");
        if (symbols.isEmpty()) {
            log.info("Watchlist is empty, no symbols to subscribe to on startup");
            return;
        }
        log.info("Subscribing to {} watchlist symbols on startup", symbols.size());
        symbols.forEach(symbol -> {
            stockSubscriptionService.subscribeToSymbol(symbol);
            log.info("Subscribed to {} watchlist symbol on startup", symbol);
        });
    }
}
