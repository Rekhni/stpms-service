package com.rakhymzhan.stmps_service.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PprUpdateRequest {
    @NotBlank
    private String type;

    @NotBlank
    private String location;

    @NotNull
    private Long crewId;

    private LocalDate q1Date;
    private LocalDate q2Date;
    private LocalDate q3Date;
    private LocalDate q4Date;
}
