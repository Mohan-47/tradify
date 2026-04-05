package com.mo.tradify.services.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.services.PriceStateService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PriceStateServiceImpl implements PriceStateService {
    private final ConcurrentHashMap<String, PriceTick> priceStateMap = new ConcurrentHashMap<>();

    @Override
    public void updatePrice(PriceTick  priceTick) {
        priceStateMap.put(priceTick.symbol(), priceTick);
    }

    @Override
    public Optional<PriceTick> getTradeResponse(String symbol) {
        return Optional.ofNullable(priceStateMap.get(symbol));
    }
}
