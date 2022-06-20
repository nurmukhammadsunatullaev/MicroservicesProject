package com.epam.microservice.songservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Configuration
public class SongConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler());
        return restTemplateBuilder.build();
    }
}

@Slf4j
class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {

        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

        }
    }
}
