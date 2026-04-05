package com.mo.tradify.events;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MarketTickEvent extends ApplicationEvent {
    private final PriceTick priceTick;

    public MarketTickEvent(Object source, PriceTick priceTick) {
        super(source);
        this.priceTick = priceTick;
    }
}
