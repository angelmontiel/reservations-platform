package com.kukultec.reservations_service.dto.request.serviceofferingservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServiceOfferingRequest (

        @NotNull(message = "Id de negocio requerido")
        Long businessId,

        @NotBlank(message = "Nombre requerido")
        String name,

        @NotNull(message = "Tiempo requerido en minutos")
        Integer durationMinutes,

        @NotNull(message = "Costo requerido")
        BigDecimal price
) {
}
