package com.rakhymzhan.stmps_service.model.dto;

public record UpdateUserRequest(
        String fullName,
        String phoneNumber
) {}
