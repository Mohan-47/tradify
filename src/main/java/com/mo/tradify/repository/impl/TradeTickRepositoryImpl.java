package com.mo.tradify.repository.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.repository.TradeTickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class TradeTickRepositoryImpl implements TradeTickRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(PriceTick priceTick) {
        jdbcTemplate.update("insert into market.trade_ticks(symbol,price,volume,trade_timestamp) values(?,?,?,?) on conflict (symbol,trade_timestamp,volume) " +
                "do nothing",
            priceTick.symbol(),
            BigDecimal.valueOf(priceTick.price()),
            BigDecimal.valueOf(priceTick.volume()),
            Timestamp.from(Instant.ofEpochMilli(priceTick.timestamp()))
        );
    }
}
