package com.example.streamingapi.controller;

import com.example.streamingapi.model.Music;
import com.example.streamingapi.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping
    public List<Music> getAllMusics() {
        return musicService.getAllMusics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        Music music = musicService.getMusicById(id);
        if (music != null) {
            return ResponseEntity.ok().body(music);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Music> createMusic(@RequestBody Music music) {
        Music createdMusic = musicService.createMusic(music);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMusic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@PathVariable Long id, @RequestBody Music music) {
        Music updatedMusic = musicService.updateMusic(id, music);
        if (updatedMusic != null) {
            return ResponseEntity.ok().body(updatedMusic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable Long id) {
        boolean deleted = musicService.deleteMusic(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
