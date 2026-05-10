package com.mo.tradify.services;

import com.mo.tradify.domain.dto.CandleResponseDto;

import java.time.Instant;

public interface CandleService {
    CandleResponseDto getCandles(String symbol, String timeframe, Instant before, Instant after, int limit);
}
