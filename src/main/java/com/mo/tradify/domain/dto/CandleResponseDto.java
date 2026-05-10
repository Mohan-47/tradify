package com.mo.tradify.domain.dto;

import java.time.Instant;
import java.util.List;

public record CandleResponseDto(
    String symbol,
    String timeframe,
    List<CandleDto> candles,
    PaginationDto pagination
) {
    public record PaginationDto(
       Instant oldest,
       Instant newest,
       int count,
       boolean hasMore
    ){}
}
