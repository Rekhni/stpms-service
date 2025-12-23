package com.rakhymzhan.stmps_service.model.dto;

public record CreateUserRequest(
        String fullName,
        String phoneNumber
) {}