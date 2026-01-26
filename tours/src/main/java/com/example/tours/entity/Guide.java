package com.example.tours.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="guide")
@Data
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_guide")
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String bio;

    @Column()
    private int rating;

    @Column(nullable = false)
    private String languages;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="number_phone", unique = true, nullable = false)
    private String phone;
}
