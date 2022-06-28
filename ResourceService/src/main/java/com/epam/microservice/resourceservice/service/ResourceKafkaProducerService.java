package com.epam.microservice.resourceservice.service;

import com.epam.microservice.resourceservice.model.ResourceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.common.serialization.StringSerializer;

@Service
@RequiredArgsConstructor
public class ResourceKafkaProducerService {
    private static final String TOPIC = "resource_model_topic";

    private final KafkaTemplate<String, ResourceModel> kafkaTemplate;

    public void sendResourceModel(ResourceModel model){
        kafkaTemplate.send(TOPIC, model);
    }
}
