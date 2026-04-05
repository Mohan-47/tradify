package com.mo.tradify.mapper;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.FinnhubTradeDto;
import com.mo.tradify.domain.dto.TradeResponseDto;

public interface PriceTickMapper {
    public TradeResponseDto mapToTradeResponseDto(PriceTick priceTick);
    public PriceTick mapToPriceTick(TradeResponseDto tradeResponseDto);
}
