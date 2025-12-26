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

    private LocalDate q1Date;
    private LocalDate q2Date;
    private LocalDate q3Date;
    private LocalDate q4Date;
}