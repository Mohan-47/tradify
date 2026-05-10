package com.mo.tradify.services.impl;

import com.mo.tradify.domain.CandleTimeframe;
import com.mo.tradify.domain.dto.CandleDto;
import com.mo.tradify.domain.dto.CandleResponseDto;
import com.mo.tradify.repository.CandleRepository;
import com.mo.tradify.services.CandleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandleServiceImpl implements CandleService {

    private static final int DEFAULT_LIMIT = 100;
    private static final int MAX_LIMIT = 500;

    private final CandleRepository candleRepository;

    @Override
    public CandleResponseDto getCandles(String symbol, String timeframe, Instant before, Instant after, int limit) {
        CandleTimeframe tf = CandleTimeframe.fromLabel(timeframe);
        // 2. enforce limit bounds
//        int safeLimit = Math.min(Math.max(limit, 1), MAX_LIMIT);
        int safeLimit = Math.clamp(limit, 1, MAX_LIMIT);

        // 3. decide which query to run based on cursors provided
        List<CandleDto> candles;

        if (before != null) {
            candles = candleRepository.findBefore(tf, symbol, before, safeLimit);
        } else if (after != null) {
            candles = candleRepository.findAfter(tf, symbol, after, safeLimit);
        } else {
            candles = candleRepository.findLatest(tf, symbol, safeLimit);
        }

        CandleResponseDto.PaginationDto pagination = buildPagination(candles, safeLimit);

        return new CandleResponseDto(symbol, timeframe, candles, pagination);
    }

    private CandleResponseDto.PaginationDto buildPagination(List<CandleDto> candles, int limit) {
        if (candles.isEmpty()) {
            return new CandleResponseDto.PaginationDto(null, null, 0, false);
        }
        Instant newest = candles.getFirst().bucket();
        Instant oldest = candles.getLast().bucket();

        // hasMore: if we got exactly limit rows, there are probably more
        boolean hasMore = candles.size() == limit;

        return new CandleResponseDto.PaginationDto(oldest, newest, candles.size(), hasMore);
    }
}
