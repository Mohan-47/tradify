package com.mo.tradify.domain;

import lombok.Builder;

@Builder
public record PriceTick(
    String symbol,
    double price,
    String timestamp,
    double volume
) {
}
