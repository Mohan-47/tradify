package com.mo.tradify.repository.impl;

import com.mo.tradify.domain.CandleTimeframe;
import com.mo.tradify.domain.dto.CandleDto;
import com.mo.tradify.repository.CandleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CandleRepositoryImpl implements CandleRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<CandleDto> CANDLE_ROW_MAPPER = (rs, rownum) -> new CandleDto(
        rs.getTimestamp("bucket").toInstant(),
        rs.getBigDecimal("open"),
        rs.getBigDecimal("high"),
        rs.getBigDecimal("low"),
        rs.getBigDecimal("close"),
        rs.getBigDecimal("volume"),
        rs.getLong("tick_count")
    );


    @Override
    public List<CandleDto> findLatest(CandleTimeframe timeframe, String symbol, int limit) {
        String sql = """
            select bucket,open,high,low,close,volume,tick_count
            from %s
            where symbol = ?
            order by bucket desc
            limit ?
            """.formatted(timeframe.getViewName());
        log.debug("findLatest: timeframe={}, symbol={}, limit={}", timeframe, symbol, limit);
        return jdbcTemplate.query(sql, CANDLE_ROW_MAPPER, symbol, limit);
    }

    @Override
    public List<CandleDto> findBefore(CandleTimeframe timeframe, String symbol, Instant before, int limit) {
        String sql = """
            SELECT bucket, open, high, low, close, volume, tick_count
            FROM %s
            WHERE symbol = ?
            AND bucket < ?
            ORDER BY bucket DESC
            LIMIT ?
            """.formatted(timeframe.getViewName());

        log.debug("findBefore: timeframe={}, symbol={}, before={}, limit={}",
            timeframe, symbol, before, limit);
        return jdbcTemplate.query(sql, CANDLE_ROW_MAPPER, symbol,
            Timestamp.from(before), limit);
    }

    @Override
    public List<CandleDto> findAfter(CandleTimeframe timeframe, String symbol, Instant after, int limit) {
        String sql = """
            SELECT bucket, open, high, low, close, volume, tick_count
            FROM %s
            WHERE symbol = ?
            AND bucket > ?
            ORDER BY bucket ASC
            LIMIT ?
            """.formatted(timeframe.getViewName());

        log.debug("findAfter: timeframe={}, symbol={}, after={}, limit={}",
            timeframe, symbol, after, limit);
        return jdbcTemplate.query(sql, CANDLE_ROW_MAPPER, symbol,
            Timestamp.from(after), limit);
    }
}
