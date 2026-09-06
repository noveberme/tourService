package com.example.tour.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name="guide")
@Data
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_guide")
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Column(nullable = false)
    private String fullname;

    @NotBlank(message = "Информация о себе не может быть пустой")
    @Column(nullable = false)
    private String bio;

    @Min(value = 0, message = "Рейтинг не может быть меньше 0")
    @Max(value = 5, message = "Рейтинг не может превышать 5")
    @Column()
    private int rating;

    @Column(nullable = false)
    private String languages;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный email")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="number_phone", unique = true, nullable = false)
    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Некорректный номер телефона")
    private String numberPhone;
}
