package com.mo.tradify.services;

import com.mo.tradify.domain.dto.FinnhubResponseDto;

public interface TradeService {
    public void processFinnhubTrades(FinnhubResponseDto finnhubResponseDto);
    //public void subscribeToSymbol(String symbol) ;
    //public void unsubscribeFromSymbol(String symbol);
}
