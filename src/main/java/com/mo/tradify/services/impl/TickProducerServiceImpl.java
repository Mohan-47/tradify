package com.mo.tradify.services.impl;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.mapper.PriceTickMapper;
import com.mo.tradify.services.TickProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class TickProducerServiceImpl implements TickProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PriceTickMapper priceTickerMapper;

    @Value("${kafka.topics.market-ticks}")
    private String marketTicksTopic;

    @Override
    public void produceTicks(PriceTick priceTick) {
        String payload = objectMapper.writeValueAsString(priceTick);
        log.info("Publishing to Kafka: {}", priceTick.symbol());
        kafkaTemplate.send(marketTicksTopic,priceTick.symbol() ,payload);
    }
}
