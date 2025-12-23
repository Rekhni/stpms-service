package com.rakhymzhan.stmps_service.model.dto;

import java.time.LocalDate;
import java.util.List;

public record CrewCreateRequest(
        String desd,
        String name,
        Long leaderId,
        List<Long> memberIds
) {}