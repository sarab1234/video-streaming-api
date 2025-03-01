package com.example.videostreaming.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String synopsis;
    private String director;
    private String genre;
    private Integer releaseYear;
    private Integer runningTime;
    private String contentUrl;
    
    private Boolean active = true; // For soft delete

    @ElementCollection
    private List<String> casting;

    private int impressions = 0;
    private int views = 0;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
