package com.epam.microservice.resourceservice.service;

import com.epam.microservice.resourceservice.model.ResourceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceKafkaProducerService {
    private static final String TOPIC = "resource_model_topic";

    private final KafkaTemplate<String, ResourceModel> kafkaTemplate;

    public void sendResourceModel(ResourceModel model){
        Message<ResourceModel> message = MessageBuilder
                .withPayload(model)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
