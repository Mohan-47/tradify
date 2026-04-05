package com.mo.tradify.listeners;

import com.mo.tradify.events.MarketTickEvent;
import com.mo.tradify.services.PriceStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceStateListener {
    private final PriceStateService priceStateService;

    @EventListener
    public void onMarketTick(MarketTickEvent marketTickEvent) {
        priceStateService.updatePrice(marketTickEvent.getPriceTick());
    }
}
