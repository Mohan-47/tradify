package com.mo.tradify.repository.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.repository.TradeTickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TradeTickRepositoryImpl implements TradeTickRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_SQL = "insert into market.trade_ticks(symbol,price,volume,trade_timestamp) values(?,?,?,?) on conflict (symbol,trade_timestamp,volume) do nothing";

    @Override
    public void insert(PriceTick priceTick) {
        jdbcTemplate.update(INSERT_SQL,
            priceTick.symbol(),
            BigDecimal.valueOf(priceTick.price()),
            BigDecimal.valueOf(priceTick.volume()),
            Timestamp.from(Instant.ofEpochMilli(priceTick.timestamp()))
        );
    }

    @Override
    public void batchInsert(List<PriceTick> priceTicks) {
        jdbcTemplate.batchUpdate(INSERT_SQL, priceTicks, priceTicks.size(),
            (ps, tick) -> {
            ps.setString(1, tick.symbol());
            ps.setBigDecimal(2, BigDecimal.valueOf(tick.price()));
            ps.setBigDecimal(3, BigDecimal.valueOf(tick.volume()));
            ps.setTimestamp(4, Timestamp.from(Instant.ofEpochMilli(tick.timestamp())));
            });
    }
}
