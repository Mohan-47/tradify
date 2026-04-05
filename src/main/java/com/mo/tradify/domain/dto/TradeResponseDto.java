package com.mo.tradify.domain.dto;

import java.io.Serializable;

public record TradeResponseDto(
    String symbol,
    double price,
    String timestamp,
    double volume
) implements Serializable {
}
