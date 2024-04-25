import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.streamingapi.model.Music;
import com.example.streamingapi.service.MusicService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musics")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public ResponseEntity<List<Music>> getAllMusics() {
        List<Music> allMusics = musicService.getAllMusics();
        return allMusics.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(allMusics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        Optional<Music> optionalMusic = musicService.getMusicById(id);
        return optionalMusic.map(music -> ResponseEntity.ok().body(music))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Music> createMusic(@RequestBody Music music) {
        if (music.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Music createdMusic = musicService.createMusic(music);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMusic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@PathVariable Long id, @RequestBody Music music) {
        if (!musicService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        Music updatedMusic = musicService.updateMusic(id, music);
        return ResponseEntity.ok().body(updatedMusic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable Long id) {
        boolean deleted = musicService.deleteMusic(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
