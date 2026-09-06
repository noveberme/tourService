package com.example.tour.entity;

import com.example.tour.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="user_tour")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Введите пароль")
    @Size(min = 6, message = "Минимальный пароль - 6 символов")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Укажите имя")
    private String fullname;

    @Column(nullable = false)
    @NotNull(message = "Укажите дату рождения")
    @Past(message = "Дата рождения должна иметь дату раньше текущего времени")
    private LocalDate birthday;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Email(message = "Некорректный email")
    private String email;

    @Column(name="number_phone", unique = true, nullable = false)
    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Некорректный номер телефона")
    private String numberPhone;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role", nullable = false)
    @NotNull(message = "Укажите роль")
    private Role userRole;
}
