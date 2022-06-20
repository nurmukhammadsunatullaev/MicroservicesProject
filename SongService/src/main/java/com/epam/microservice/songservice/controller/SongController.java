package com.epam.microservice.songservice.controller;

import com.epam.microservice.songservice.model.SongModel;
import com.epam.microservice.songservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<SongModel>> getSongs(){
        return ResponseEntity.ok(songService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongModel> getSong(@PathVariable Long id){
        return songService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SongModel> postSong(@RequestBody SongModel model){
        return songService.save(model)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<List<SongModel>> delete(@PathVariable Long[] ids){
        return ResponseEntity.ok(songService.delete(ids));
    }
}
