package com.mo.tradify.repository;

import com.mo.tradify.domain.CandleTimeframe;
import com.mo.tradify.domain.dto.CandleDto;

import java.time.Instant;
import java.util.List;

public interface CandleRepository {
    List<CandleDto> findLatest(CandleTimeframe timeframe, String symbol, int limit);

    List<CandleDto> findBefore(CandleTimeframe timeframe, String symbol, Instant before, int limit);

    List<CandleDto> findAfter(CandleTimeframe timeframe, String symbol, Instant after, int limit);
}

