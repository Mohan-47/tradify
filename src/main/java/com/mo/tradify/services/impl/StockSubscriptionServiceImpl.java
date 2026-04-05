package com.mo.tradify.services.impl;

import com.mo.tradify.services.StockSubscriptionService;
import com.mo.tradify.websocket.FinnhubInboundHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockSubscriptionServiceImpl implements StockSubscriptionService {

    private final FinnhubInboundHandler finnhubInboundHandler;

    @Override
    public void subscribeToSymbol(String symbol) {
        try{
            finnhubInboundHandler.subscribeToSymbol(symbol);
        }
        catch(Exception e){
            log.error("Error while subscribing to symbol {}", symbol, e);
        }
    }

    @Override
    public void unsubscribeFromSymbol(String symbol) {
        try{
            finnhubInboundHandler.unsubscribeFromSymbol(symbol);
        }
        catch (Exception e){
            log.error("Error while unsubscribing from symbol {}", symbol, e);
        }
    }
}
