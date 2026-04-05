//package com.mo.tradify.listeners;
//
//import com.mo.tradify.domain.PriceTick;
//import com.mo.tradify.events.MarketTickEvent;
//import com.mo.tradify.mapper.impl.PriceTickMapperImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.event.EventListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//import tools.jackson.databind.ObjectMapper;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class KafkaProducerListener {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//    private final PriceTickMapperImpl priceTickerMapper;
//
//    @Value("${kafka.topics.market-ticks}")
//    private String marketTicksTopic;
//
//    @EventListener
//    public void onMarketTick(MarketTickEvent event) {
//        try {
//            PriceTick priceTick = event.getPriceTick();
////            TradeResponseDto tradeResponseDto = priceTickerMapper.mapToTradeResponseDto(priceTick);
//            String payload = objectMapper.writeValueAsString(priceTick);
//            log.info("Publishing to Kafka: {}", priceTick.symbol());
////            kafkaTemplate.send(marketTicksTopic, priceTick.symbol(), payload);
//        } catch (Exception e) {
//            log.error("Failed to publish to Kafka: {}", e.getMessage());
//        }
//    }
//}