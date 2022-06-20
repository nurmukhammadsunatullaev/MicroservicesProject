package com.epam.microservice.resourceservice.repository;

import com.epam.microservice.resourceservice.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

}
