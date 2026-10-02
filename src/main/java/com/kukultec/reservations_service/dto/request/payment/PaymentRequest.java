package com.kukultec.reservations_service.dto.request.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentRequest (

        @NotNull(message = "Id de cita requerido")
        Long appointmentId,

        @NotNull(message = "Monto requerido")
        @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
        BigDecimal amount,

        @NotBlank(message = "Metodo de pago requerido")
        String method

) {}