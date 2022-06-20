package com.epam.microservice.songservice.mapper;

import com.epam.microservice.songservice.entity.SongEntity;
import com.epam.microservice.songservice.model.SongModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongModel toDto(SongEntity entity);
    SongEntity toEntity(SongModel model);
    List<SongModel> toDtos(List<SongEntity> songs);
    List<SongEntity> toEntities(List<SongModel> songs);
}
