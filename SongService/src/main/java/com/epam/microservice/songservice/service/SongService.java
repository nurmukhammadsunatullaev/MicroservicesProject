package com.epam.microservice.songservice.service;

import com.epam.microservice.songservice.entity.SongEntity;
import com.epam.microservice.songservice.mapper.SongMapper;
import com.epam.microservice.songservice.model.ResourceModel;
import com.epam.microservice.songservice.model.SongModel;
import com.epam.microservice.songservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongMapper mapper;
    private final SongRepository songRepository;

    private final ResourceService resourceService;
    @Transactional
    public Optional<SongModel> save(SongModel model) {

        Optional<ResourceModel> optional = resourceService.getResourceById(model.getResourceId());
        if(optional.isEmpty()){
            return Optional.empty();
        }
        SongEntity entity = mapper.toEntity(model);
        songRepository.save(entity);
        return Optional.of(mapper.toDto(entity));
    }

    public Optional<SongModel> getById(Long id) {
        return songRepository.findById(id).map(mapper::toDto);
    }

    public List<SongModel> getAll() {
        return mapper.toDtos(songRepository.findAll());
    }

    public List<SongModel> delete(Long[] ids) {
        List<SongModel> resources=new ArrayList<>();
        for (Long id : ids) {
            Optional<SongEntity> entity=songRepository.findById(id);
            if(entity.isPresent()){
                SongEntity song=entity.get();
                resources.add(mapper.toDto(song));
                songRepository.delete(song);
            }
        }
        return resources;
    }
}


