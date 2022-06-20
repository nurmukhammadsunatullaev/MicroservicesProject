package com.epam.microservice.resourceservice.mapper;

import com.epam.microservice.resourceservice.entity.ResourceEntity;
import com.epam.microservice.resourceservice.model.ResourceModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    ResourceModel toDto(ResourceEntity entity);
    List<ResourceModel> toDtos(List<ResourceEntity> entities);
}
