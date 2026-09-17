package com.example.tour.dto;

import com.example.tour.enums.Role;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String numberPhone;
    private LocalDate birthday;
    private Role userRole;
}
