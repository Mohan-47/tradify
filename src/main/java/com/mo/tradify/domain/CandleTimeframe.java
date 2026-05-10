package com.mo.tradify.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CandleTimeframe {
    ONE_MIN("1min", "market.ohlcv_1min"),
    FIVE_MIN("5min", "market.ohlcv_5min"),
    ONE_HOUR("1hour", "market.ohlcv_1hour"),
    ONE_DAY("1day", "market.ohlcv_1day");

    private final String label;
    private final String viewName;

    public static CandleTimeframe fromLabel(String label) {
        for (CandleTimeframe c : values()) {
            if (c.label.equalsIgnoreCase(label)) {
                return c;
            }
        }
        throw new IllegalArgumentException(
            "Invalid timeframe: " + label + ". Valid values: 1min, 5min, 1hour, 1day"
        );
    }
}
