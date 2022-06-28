package com.epam.microservice.resourceprocessor.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResourceModel implements Serializable {
    private Long id;
    private String path;
}
