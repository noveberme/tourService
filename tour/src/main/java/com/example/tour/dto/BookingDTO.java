package com.example.tour.dto;

import com.example.tour.enums.Status;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDTO {
    private Long id;
    private String tourTitle;
    private Long tourId;
    private String userName;
    private LocalDate date;
    private Integer countParticipants;
    private BigDecimal totalPrice;
    private Status status;
}
