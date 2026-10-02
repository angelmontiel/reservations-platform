package com.kukultec.reservations_service.dto.response.user;

import com.kukultec.reservations_service.enums.UserRole;

public record UserResponse (

        Long id,
        Long businessId,
        String username,
        String email,
        UserRole role

) {}