package com.epam.microservice.resourceprocessor.service;

import com.epam.microservice.resourceprocessor.model.SongModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {
    private final RestTemplate restTemplate;
    @Value("${song.service.base.url}")
    private String baseUrl;
    public Optional<SongModel> postSong(SongModel model){
        ResponseEntity<SongModel> response = restTemplate.postForEntity(baseUrl,model,SongModel.class);
        if(response.getStatusCode() == HttpStatus.OK){
            return Optional.ofNullable(response.getBody());
        }
        return Optional.empty();
    }
}
