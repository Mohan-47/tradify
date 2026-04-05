package com.mo.tradify.listeners;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.domain.dto.TradeResponseDto;
import com.mo.tradify.events.MarketTickEvent;
import com.mo.tradify.mapper.PriceTickMapper;
import com.mo.tradify.mapper.impl.PriceTickMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerListener {

    private final ObjectMapper objectMapper;
    private final PriceTickMapper priceTickMapper;
    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = "${kafka.topics.market-ticks}",
        groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        try {
            PriceTick priceTick = objectMapper.readValue(message, PriceTick.class);
            log.info("Consumed from Kafka → key: {} symbol: {} price: {} volume: {}",
                key,
                priceTick.symbol(),
                priceTick.price(),
                priceTick.volume()
            );
            eventPublisher.publishEvent(new MarketTickEvent(this,priceTick));
        } catch (Exception e) {
            log.error("Failed to deserialize message: {}", e.getMessage());
        }
    }
}
