package com.kukultec.reservations_service.dto.response.employee;

public record EmployeeResponse (

        Long id,
        Long businessId,
        String fullName,
        String email,
        Boolean active

) {}