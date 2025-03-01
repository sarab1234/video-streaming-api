package com.example.videostreaming.repository;

import com.example.videostreaming.model.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
    Engagement findByVideoId(Long videoId);
}
