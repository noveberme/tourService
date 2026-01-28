package com.example.tour.entity;

import com.example.tour.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_booking")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_tour", nullable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    @Column(name="date_booking", nullable = false)
    private LocalDate date;

    @Column(name="count_participation", nullable = false)
    private Integer countParticipants;

    @Column(name="total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Status status;
}
