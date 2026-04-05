package com.mo.tradify.mapper;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.FinnhubResponseDto;
import com.mo.tradify.domain.dto.FinnhubTradeDto;
import com.mo.tradify.domain.dto.TradeResponseDto;

public interface TradeResponseMapper {
    public PriceTick mapToPriceTick(FinnhubTradeDto finnhubTradeDto);
    public FinnhubTradeDto mapToFinnhubTradeDto(PriceTick priceTick);
}
