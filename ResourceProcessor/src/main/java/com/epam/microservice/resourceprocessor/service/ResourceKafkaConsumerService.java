package com.epam.microservice.resourceprocessor.service;

import com.epam.microservice.resourceprocessor.model.ResourceModel;
import com.epam.microservice.resourceprocessor.model.SongModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceKafkaConsumerService {
    private final ProcessorService processorService;
    @KafkaListener(topics = "resource_model_topic", groupId = "resource_group")
    public void consumeResourceModel(ResourceModel model){
        System.out.println(model);
         Optional<SongModel> resource =  processorService.extractFile(model);
         resource.ifPresent(songModel -> log.info("resource", songModel));
    }

}