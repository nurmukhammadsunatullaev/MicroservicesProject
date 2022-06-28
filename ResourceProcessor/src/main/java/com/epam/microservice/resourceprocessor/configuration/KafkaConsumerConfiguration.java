package com.epam.microservice.resourceprocessor.configuration;

import com.epam.microservice.resourceprocessor.model.ResourceModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrap_servers;
    @Value("${spring.kafka.consumer.group-id}")
    private String group_id;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String auto_offset_reset;
    @Bean
    public ConsumerFactory<String, ResourceModel> consumerFactory(){
        JsonDeserializer<ResourceModel> deserializer = new JsonDeserializer<>(ResourceModel.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ResourceModel> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ResourceModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;

    }
}