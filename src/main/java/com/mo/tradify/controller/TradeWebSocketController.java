package com.mo.tradify.controller;

import com.mo.tradify.services.PriceStateService;
import com.mo.tradify.services.StockSubscriptionService;
import com.mo.tradify.services.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TradeWebSocketController {
    private final StockSubscriptionService stockSubscriptionService;
    private final PriceStateService priceStateService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/trades/subscribe")
    public void subscribe(@Payload String symbol, SimpMessageHeaderAccessor headerAccessor) {

        stockSubscriptionService.subscribeToSymbol(symbol.toUpperCase());
        priceStateService.getTradeResponse(symbol.toUpperCase())
            .ifPresent(tradeResponseDto -> {
                simpMessagingTemplate.convertAndSendToUser(
                    headerAccessor.getSessionId(),
                    "/topic/trades/" + symbol.toUpperCase(),
                    tradeResponseDto
                );
            });

    }

    @MessageMapping("/trades/unsubscribe")
    public void unsubscribe(@Payload String symbol){
        stockSubscriptionService.unsubscribeFromSymbol(symbol.toUpperCase());
    }



}

