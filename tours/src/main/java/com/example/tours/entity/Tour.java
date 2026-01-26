package com.example.tours.entity;

import com.example.tours.enums.Language;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tour")
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tour")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_guide", nullable = false)
    private Guide idGuide;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double duration;

    @Column(nullable = false)
    private int price;

    @Column(name="max_participants", nullable = false)
    private int maxParticipants;

    @Column(name="start_location", columnDefinition = "TEXT")
    private String startLocation;

    @Enumerated(EnumType.STRING)
    @Column(name="language_tour", length = 200)
    private Language tourLanguage;
}
