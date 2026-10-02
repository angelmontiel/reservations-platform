package com.kukultec.reservations_service.dto.response.customer;

public record CustomerResponse (

        Long id,
        String fullName,
        String email,
        String phone

) {}