package com.mo.tradify.services.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.FinnhubResponseDto;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.events.MarketTickEvent;
import com.mo.tradify.mapper.TradeResponseMapper;
import com.mo.tradify.services.TickProducerService;
import com.mo.tradify.services.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final TradeResponseMapper tradeResponseMapper;
    //private final ApplicationEventPublisher eventPublisher;
    private final TickProducerService tickProducerService;

    @Override
    public void processFinnhubTrades(FinnhubResponseDto response) {
        if(!"trade".equals(response.getType())) return;

        response.getData().forEach(trade -> {
            PriceTick priceTick = tradeResponseMapper.mapToPriceTick(trade);

            tickProducerService.produceTicks(priceTick);
            //eventPublisher.publishEvent(new MarketTickEvent(this, priceTick));
        });
    }
}
