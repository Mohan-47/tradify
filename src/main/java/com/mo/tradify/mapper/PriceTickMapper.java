package com.mo.tradify.mapper;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;

public interface PriceTickMapper {
    TradeResponseDto mapToTradeResponseDto(PriceTick priceTick);
}
