package com.example.videostreaming.repository;

import com.example.videostreaming.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByActiveTrue(); // Fetch only active videos

    List<Video> findByDirectorContainingAndActiveTrue(String director);

    List<Video> findByGenreContainingAndActiveTrue(String genre);

    List<Video> findByReleaseYearAndActiveTrue(Integer releaseYear);
}
