package com.mo.tradify.domain;

import lombok.Builder;

@Builder
public record PriceTick(
    String symbol,
    double price,
    long timestamp,
    double volume
) {
}
