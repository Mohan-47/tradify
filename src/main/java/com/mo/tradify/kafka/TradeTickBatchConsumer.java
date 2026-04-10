package com.mo.tradify.kafka;

import com.mo.tradify.domain.PriceTick;
import com.mo.tradify.repository.TradeTickRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeTickBatchConsumer {
    private final TradeTickRepository tradeTickRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(
        topics = "${kafka.topics.market-ticks}",
        groupId = "tradify-storage",
        containerFactory = "batchListenerContainerFactory"
    )
    public void consumeBatch(List<String> payload){
        log.info("Received batch of {} trade ticks", payload.size());

        List<PriceTick> ticks = payload.stream()
            .map(this::deserialize)
            .filter(Objects::nonNull)
            .toList();

        if(!ticks.isEmpty()){
            tradeTickRepository.batchInsert(ticks);
            log.info("Inserted batch of {} ticks",ticks.size());
        }

    }

    private PriceTick deserialize(String payload){
        try {
            return objectMapper.readValue(payload, PriceTick.class);
        } catch (Exception e){
          log.error("Failed to deserialize {} : {}", payload, e.getMessage());
          return null;
        }
    }
}
