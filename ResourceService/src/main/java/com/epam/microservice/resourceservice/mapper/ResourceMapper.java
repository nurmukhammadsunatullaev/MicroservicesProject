package com.epam.microservice.resourceservice.mapper;

import com.epam.microservice.resourceservice.entity.ResourceEntity;
import com.epam.microservice.resourceservice.model.ResourceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    ResourceModel toDto(ResourceEntity entity);
}
