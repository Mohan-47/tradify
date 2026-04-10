package com.mo.tradify.repository;

import com.mo.tradify.domain.PriceTick;

public interface TradeTickRepository {
    public void insert(PriceTick priceTick);
}
