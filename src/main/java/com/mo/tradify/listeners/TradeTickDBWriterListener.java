package com.mo.tradify.listeners;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.events.MarketTickEvent;
import com.mo.tradify.repository.TradeTickRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TradeTickDBWriterListener {
    private final TradeTickRepository tradeTickRepository;

    @EventListener
    public void onMarketTick(MarketTickEvent marketTickEvent) {
        PriceTick priceTick = marketTickEvent.getPriceTick();
        try{
            tradeTickRepository.insert(priceTick);
            log.info("Trade Tick Inserted {} : {}", priceTick.symbol(), priceTick.price());
        }
        catch (Exception e){
            log.error("Error while inserting trade tick into DB : {}", e.getMessage());
        }
    }
}
