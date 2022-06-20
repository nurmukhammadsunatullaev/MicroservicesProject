package com.epam.microservice.songservice.service;

import com.epam.microservice.songservice.model.ResourceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {

    @Value("${resource.service.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public Optional<ResourceModel> getResourceById(Long id){
        ResponseEntity<ResourceModel> response = restTemplate.getForEntity(baseUrl.concat("data/"+id),ResourceModel.class);
        if(response.getStatusCode() == HttpStatus.OK){
            return Optional.ofNullable(response.getBody());
        }
        return Optional.empty();
    }

}
