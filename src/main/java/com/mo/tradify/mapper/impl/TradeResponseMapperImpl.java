package com.mo.tradify.mapper.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.FinnhubTradeDto;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.mapper.TradeResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class TradeResponseMapperImpl implements TradeResponseMapper {
    @Override
    public PriceTick mapToPriceTick(FinnhubTradeDto finnhubTradeDto) {
        return PriceTick.builder()
            .symbol(finnhubTradeDto.getSymbol())
            .price(finnhubTradeDto.getPrice())
            .timestamp(finnhubTradeDto.getTimestamp())
            .volume(finnhubTradeDto.getVolume())
            .build();
    }

    public FinnhubTradeDto mapToFinnhubTradeDto(PriceTick priceTick) {
        return new FinnhubTradeDto(
            priceTick.symbol(),
            priceTick.price(),
            priceTick.timestamp(),
            priceTick.volume()
        );
    }
}
