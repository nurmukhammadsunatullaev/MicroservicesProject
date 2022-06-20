package com.epam.microservice.resourceservice.service;

import com.epam.microservice.resourceservice.entity.ResourceEntity;
import com.epam.microservice.resourceservice.mapper.ResourceMapper;
import com.epam.microservice.resourceservice.model.ResourceModel;
import com.epam.microservice.resourceservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper mapper;

    @Transactional
    public Optional<ResourceModel> saveFile(MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/upload/"+Objects.requireNonNull(file.getOriginalFilename()));
            Files.write(path, bytes);
            ResourceEntity entity = new ResourceEntity();
            entity.setPath(path.toString());
            resourceRepository.save(entity);
            return Optional.of(mapper.toDto(entity));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Optional<ResourceModel> getResourceById(Long id) {
        return resourceRepository.findById(id).map(mapper::toDto);
    }

    public List<ResourceModel> getAllResources() {
        return mapper.toDtos(resourceRepository.findAll());
    }

    public List<ResourceModel> delete(Long[] ids) {
        List<ResourceModel> resources=new ArrayList<>();
        for (Long id : ids) {
            Optional<ResourceEntity> entity=resourceRepository.findById(id);
            if(entity.isPresent()){
                ResourceEntity resource=entity.get();
                resources.add(mapper.toDto(resource));
                resourceRepository.delete(resource);
            }
        }
        return resources;
    }
}
