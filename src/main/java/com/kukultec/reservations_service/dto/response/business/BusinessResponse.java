package com.kukultec.reservations_service.dto.response.business;

public record BusinessResponse(
        Long id,
        String name,
        String address,
        String telephone,
        Boolean active
) {}
