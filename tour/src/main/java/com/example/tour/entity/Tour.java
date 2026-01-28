package com.example.tour.entity;

import com.example.tour.enums.Language;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="tour")
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tour")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_guide", nullable = false)
    private Guide guide;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double duration;

    @Setter
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
