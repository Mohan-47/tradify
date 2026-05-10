package com.mo.tradify.controller;

import com.mo.tradify.domain.dto.CandleResponseDto;
import com.mo.tradify.services.CandleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@Slf4j
@RequestMapping("/api/candles")
@RequiredArgsConstructor
public class CandleController {
    private final CandleService candleService;

    @GetMapping
    public ResponseEntity<CandleResponseDto> getCandles(
        @RequestParam String symbol,
        @RequestParam String timeframe,
        @RequestParam(required = false) Instant before,
        @RequestParam(required = false) Instant after,
        @RequestParam(defaultValue = "100") int limit
    ) {
        log.info("Candle request: symbol={}, timeframe={}, before={}, after={}, limit={}",
            symbol, timeframe, before, after, limit);

        CandleResponseDto response = candleService.getCandles(
            symbol, timeframe, before, after, limit
        );

        return ResponseEntity.ok(response);
    }
}
