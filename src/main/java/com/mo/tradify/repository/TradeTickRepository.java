package com.mo.tradify.repository;

import com.mo.tradify.domain.PriceTick;

import java.util.List;

public interface TradeTickRepository {
    void insert(PriceTick priceTick);
    void batchInsert(List<PriceTick> priceTicks);
}
