package com.epam.microservice.resourceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import com.epam.microservice.resourceservice.entity.base.AuditableEntity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class ResourceEntity extends AuditableEntity {
    private String path;
}
