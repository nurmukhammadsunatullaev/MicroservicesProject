package com.epam.microservice.resourceservice.service;

import com.epam.microservice.resourceservice.entity.ResourceEntity;
import com.epam.microservice.resourceservice.mapper.ResourceMapper;
import com.epam.microservice.resourceservice.model.ResourceModel;
import com.epam.microservice.resourceservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper mapper;

    @Transactional
    public Long saveFile(MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/upload/"+Objects.requireNonNull(file.getOriginalFilename()));
            Files.write(path, bytes);
            ResourceEntity entity = new ResourceEntity();
            entity.setPath(path.toString());
            resourceRepository.save(entity);
            return entity.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ResourceModel> getResourceById(Long id) {
        return resourceRepository.findById(id).map(mapper::toDto);
    }
}
