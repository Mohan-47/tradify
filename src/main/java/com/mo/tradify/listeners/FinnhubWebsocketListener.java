package com.mo.tradify.listeners;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.events.MarketTickEvent;
import com.mo.tradify.mapper.impl.PriceTickMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FinnhubWebsocketListener {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PriceTickMapperImpl priceTickerMapper;

    @EventListener
    public void onMarketTick(MarketTickEvent marketTickEvent) {
        PriceTick priceTick = marketTickEvent.getPriceTick();
        TradeResponseDto tradeResponseDto = priceTickerMapper.mapToTradeResponseDto(priceTick);
        if (tradeResponseDto != null) {
//            log.info("Broadcasting trade : {} timestamp : {}", tradeResponseDto.symbol(), tradeResponseDto.timestamp());
            simpMessagingTemplate.convertAndSend("/topic/trades/" + tradeResponseDto.symbol(), tradeResponseDto);
        }
    }
}
