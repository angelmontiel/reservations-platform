package com.kukultec.reservations_service.dto.response.serviceofferingservice;

import java.math.BigDecimal;

public record ServiceOfferingResponse(

        Long id,
        Long businessId,
        String name,
        Integer durationMinutes,
        BigDecimal price
){
}
