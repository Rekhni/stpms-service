package com.rakhymzhan.stmps_service.model.dto;

public record UserResponse(
        Long id,
        String fullName,
        String phoneNumber
) {}
