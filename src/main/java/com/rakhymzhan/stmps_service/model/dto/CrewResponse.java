package com.rakhymzhan.stmps_service.model.dto;

import java.time.LocalDate;
import java.util.List;

public record CrewResponse(
        Long id,
        String desd,
        String name,
        Long leaderId,
        String leaderFullName,
        List<CrewMemberShort> members
) {
    public record CrewMemberShort(
            Long id,
            String fullName,
            String phoneNumber
    ) {}
}
