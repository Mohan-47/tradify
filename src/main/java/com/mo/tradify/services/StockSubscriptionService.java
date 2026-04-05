package com.mo.tradify.services;

public interface StockSubscriptionService {
    void subscribeToSymbol(String symbol);
    void unsubscribeFromSymbol(String symbol);
}
