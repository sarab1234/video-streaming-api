package com.example.videostreaming.controller;

import com.example.videostreaming.model.Video;
import com.example.videostreaming.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// just for testing for commit
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<Video> publishVideo(@RequestBody Video video) {
        return ResponseEntity.ok(videoService.publishVideo(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        return ResponseEntity.ok(videoService.updateVideo(id, video));
    }

    @PatchMapping("/{id}/delist")
    public ResponseEntity<String> delistVideo(@PathVariable Long id) {
        videoService.delistVideo(id);
        return ResponseEntity.ok("Video has been delisted successfully.");
    }

    @GetMapping
    public ResponseEntity<List<Video>> listAllVideos() {
        return ResponseEntity.ok(videoService.listAllVideos());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Video>> searchVideos(@RequestParam(required = false) String director,
                                                    @RequestParam(required = false) String genre,
                                                    @RequestParam(required = false) Integer releaseYear) {
        if (director != null) return ResponseEntity.ok(videoService.searchVideosByDirector(director));
        if (genre != null) return ResponseEntity.ok(videoService.searchVideosByGenre(genre));
        if (releaseYear != null) return ResponseEntity.ok(videoService.searchVideosByReleaseYear(releaseYear));
        return ResponseEntity.badRequest().build();
    }
    
    
    
 // Load video metadata + content URL
    @GetMapping("/{id}/load")
    public ResponseEntity<Video> loadVideo(@PathVariable Long id) {
    	 videoService.incrementImpressions(id);
         return videoService.getVideo(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    // Play video (mocked content)
    @GetMapping("/{id}/play")
    public ResponseEntity<String> playVideo(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.playVideo(id));
    }
    
    
    
    
    
    
    
    
    
}
