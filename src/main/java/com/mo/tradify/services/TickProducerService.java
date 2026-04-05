package com.mo.tradify.services;

import com.mo.tradify.domain.PriceTick;

public interface TickProducerService {
    public void produceTicks(PriceTick priceTick);
}
