package com.example.tours.entity;

import com.example.tours.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="user_tour")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private int id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="number_phone", unique = true, nullable = false)
    private String numberPhone;

    @Column(name="user_role", nullable = false)
    private Role userRole;
}
