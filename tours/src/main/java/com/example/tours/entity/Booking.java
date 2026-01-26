package com.example.tours.entity;

import com.example.tours.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="guide")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_booking")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_tour", nullable = false)
    private Tour idTour;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User idUser;

    @Column(name="date_booking", nullable = false)
    private LocalDate date;

    @Column(name="count_participation", nullable = false)
    private int countParticipants;

    @Column(name="total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Status status;
}
