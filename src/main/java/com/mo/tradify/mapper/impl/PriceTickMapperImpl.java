package com.mo.tradify.mapper.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.mapper.PriceTickMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceTickMapperImpl implements PriceTickMapper {
    @Override
    public TradeResponseDto mapToTradeResponseDto(PriceTick priceTick) {
        return new TradeResponseDto(
            priceTick.symbol(),
            priceTick.price(),
            priceTick.timestamp(),
            priceTick.volume()
        );
    }

    @Override
    public PriceTick mapToPriceTick(TradeResponseDto tradeResponseDto) {
        return PriceTick.builder()
            .symbol(tradeResponseDto.symbol())
            .price(tradeResponseDto.price())
            .timestamp(tradeResponseDto.timestamp())
            .volume(tradeResponseDto.volume())
            .build();
    }
}
