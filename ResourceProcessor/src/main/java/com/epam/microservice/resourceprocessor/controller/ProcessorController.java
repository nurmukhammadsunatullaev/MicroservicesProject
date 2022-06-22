package com.epam.microservice.resourceprocessor.controller;

import com.epam.microservice.resourceprocessor.model.ResourceModel;
import com.epam.microservice.resourceprocessor.model.SongModel;
import com.epam.microservice.resourceprocessor.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/processor")
@RequiredArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;
    @PostMapping
    public ResponseEntity<SongModel> post(@RequestBody ResourceModel resource){
        return processorService.extractFile(resource).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

}
