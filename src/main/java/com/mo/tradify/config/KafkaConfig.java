package com.mo.tradify.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Configuration
public class KafkaConfig {

    @Value("${kafka.topics.market-ticks}")
    private String marketTicksTopic;

    @Bean
    public NewTopic createMarketTicksTopic() {
        log.info("Creating Kafka topic: {}", marketTicksTopic);
        return TopicBuilder.name(marketTicksTopic)
            .partitions(6)
            .replicas(1)
            .build();
    }
}