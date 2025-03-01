package com.example.videostreaming.service;

import com.example.videostreaming.model.Video;
import com.example.videostreaming.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public Video publishVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video updateVideo(Long id, Video updatedVideo) {
        return videoRepository.findById(id).map(video -> {
            video.setTitle(updatedVideo.getTitle());
            video.setSynopsis(updatedVideo.getSynopsis());
            video.setDirector(updatedVideo.getDirector());
            video.setGenre(updatedVideo.getGenre());
            video.setReleaseYear(updatedVideo.getReleaseYear());
            video.setRunningTime(updatedVideo.getRunningTime());
            video.setContentUrl(updatedVideo.getContentUrl());
            video.setCasting(updatedVideo.getCasting());
            return videoRepository.save(video);
        }).orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public void delistVideo(Long id) {
        videoRepository.findById(id).ifPresent(video -> {
            video.setActive(false);
            videoRepository.save(video);
        });
    }

    public Optional<Video> getVideo(Long id) {
        return videoRepository.findById(id).filter(Video::getActive);
    }

    public List<Video> listAllVideos() {
        return videoRepository.findByActiveTrue();
    }

    public List<Video> searchVideosByDirector(String director) {
        return videoRepository.findByDirectorContainingAndActiveTrue(director);
    }

    public List<Video> searchVideosByGenre(String genre) {
        return videoRepository.findByGenreContainingAndActiveTrue(genre);
    }

    public List<Video> searchVideosByReleaseYear(Integer year) {
        return videoRepository.findByReleaseYearAndActiveTrue(year);
    }

    public void incrementImpressions(Long id) {
        videoRepository.findById(id).ifPresent(video -> {
            video.setImpressions(video.getImpressions() + 1);
            videoRepository.save(video);
        });
    }

    public void incrementViews(Long id) {
        videoRepository.findById(id).ifPresent(video -> {
            video.setViews(video.getViews() + 1);
            videoRepository.save(video);
        });
    }
    
    
    public Video loadVideo(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public String playVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        // Increase the views count when playing
        video.setViews(video.getViews() + 1);
        videoRepository.save(video);

        return "Playing video: " + video.getTitle() + " from URL: " + video.getContentUrl();
    }

    
    
    
    
}
