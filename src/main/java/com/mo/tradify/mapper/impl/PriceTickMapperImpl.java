package com.mo.tradify.mapper.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.mapper.PriceTickMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PriceTickMapperImpl implements PriceTickMapper {
    @Override
    public TradeResponseDto mapToTradeResponseDto(PriceTick priceTick) {
        return new TradeResponseDto(
            priceTick.symbol(),
            priceTick.price(),
            Instant.ofEpochMilli(priceTick.timestamp()).toString(),
            priceTick.volume()
        );
    }
}
