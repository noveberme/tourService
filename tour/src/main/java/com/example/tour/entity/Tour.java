package com.example.tour.entity;

import com.example.tour.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    @NotBlank
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String description;

    @Column(nullable = false)
    @Positive(message = "Длительность должна быть больше 0")
    private Double duration;

    @Setter
    @Column(nullable = false)
    @Min(value = 1, message = "Цена не может быть отрицательной")
    private Integer price;

    @Column(name="max_participants", nullable = false)
    @Min(value = 1, message = "Должен быть минимум 1 участник")
    private Integer maxParticipants;

    @Column(name="start_location", columnDefinition = "TEXT")
    private String startLocation;

    @Enumerated(EnumType.STRING)
    @Column(name="language_tour", length = 200)
    private Language tourLanguage;
}
