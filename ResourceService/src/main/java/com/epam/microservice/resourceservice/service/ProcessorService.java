package com.epam.microservice.resourceservice.service;

import com.epam.microservice.resourceservice.model.ResourceModel;
import com.epam.microservice.resourceservice.model.SongModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessorService {

    @Value("${processor.service.base.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    public Optional<SongModel> postProcessor(ResourceModel resource)  {
        ResponseEntity<SongModel> response = restTemplate.postForEntity(baseUrl,resource, SongModel.class);
        if(response.getStatusCode() == HttpStatus.OK){
            return Optional.ofNullable(response.getBody());
        }
        return Optional.empty();
    }
}
