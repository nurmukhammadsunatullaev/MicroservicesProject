package com.epam.microservice.resourceservice.controller;

import com.epam.microservice.resourceservice.model.ResourceModel;
import com.epam.microservice.resourceservice.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping
    public ResponseEntity<List<ResourceModel>> get(){
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id){
            return resourceService.getResourceById(id)
                    .map(element->{
                        String path = element.getPath();
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                        headers.add("Pragma", "no-cache");
                        headers.add("Expires", "0");
                        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.substring(path.lastIndexOf("/")));
                        try {
                            byte []  content = Files.readAllBytes(Paths.get(path));
                            return new  ResponseEntity<>(content,headers, HttpStatus.OK);
                        } catch (IOException e) {
                            return ResponseEntity.internalServerError().build();
                        }
                    }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ResourceModel> getResourceDataById(@PathVariable Long id){
        return resourceService.getResourceById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResourceModel> post(@RequestParam("file") MultipartFile file){
        return resourceService.saveFile(file).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<List<ResourceModel>> delete(@PathVariable Long[] ids){
        return ResponseEntity.ok(resourceService.delete(ids));
    }
}
