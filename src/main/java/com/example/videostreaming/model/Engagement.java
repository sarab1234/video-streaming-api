package com.example.videostreaming.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "engagements")
@Data
public class Engagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Video video;

    private int impressions = 0;
    private int views = 0;
}
