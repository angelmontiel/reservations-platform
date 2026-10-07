package com.kukultec.reservations_service.dto.request.appointment;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest (

        @NotNull(message = "Id de servicio requerido")
        Long serviceOfferingId,

        @NotNull(message = "Id de empleado requerido")
        Long employeeId,

        @NotNull(message = "Id de cliente requerido")
        Long customerId,

        @NotNull(message = "Fecha requerida")
        @FutureOrPresent(message = "La fecha no puede ser en el pasado")
        LocalDate date,

        @NotNull(message = "Hora de comienzo requerida")
        LocalTime startTime,

        @NotNull(message = "Hora de finalizacion requerida")
        LocalTime endTime

) {}