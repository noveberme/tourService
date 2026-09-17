package com.example.tour.dto;

import com.example.tour.enums.Language;
import lombok.Data;

@Data
public class TourDTO {
    private Long id;
    private String title;
    private String description;
    private Double duration;
    private Integer price;
    private Integer maxParticipants;
    private String startLocation;
    private Language tourLanguage;
    private Long guideId;
    private String guideName;
}
