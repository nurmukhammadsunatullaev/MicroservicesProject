package com.epam.microservice.resourceservice;

import com.epam.microservice.resourceservice.controller.ResourceController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResourceServiceApplicationTests {

    @Autowired
    private ResourceController controller;

    @Test
    public void resourceControllerTest(){
        assertThat(controller).isNotNull();
    }


}
