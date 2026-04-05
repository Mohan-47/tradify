package com.mo.tradify.services;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;

import java.util.Optional;

public interface PriceStateService {

    public void updatePrice(PriceTick priceTick);
    public Optional<PriceTick> getTradeResponse(String symbol);
}