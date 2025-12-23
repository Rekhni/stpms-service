package com.rakhymzhan.stmps_service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PprResponse {
    private Long id;
    private String type;
    private String location;

    private Long crewId;
    private String crewName;
    private String leaderFullName;

    // Если хочешь показывать "Ответственный" = бригадир
    private String responsible;

    // Если хочешь показывать "Кол-во объектов" — лучше брать из Crew
    private Integer objectsQuantity;

    private LocalDate q1Date;
    private LocalDate q2Date;
    private LocalDate q3Date;
    private LocalDate q4Date;
}