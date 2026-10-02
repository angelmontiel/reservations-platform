package com.kukultec.reservations_service.dto.request.schedule;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ScheduleRequest (

        @NotNull(message = "Id de empleado requerido")
        Long employeeId,

        @NotNull(message = "Día de la semana requerido")
        DayOfWeek dayOfWeek,

        @NotNull(message = "Hora de inicio requerida")
        LocalTime startTime,

        @NotNull(message = "Hora de fin requerida")
        LocalTime endTime

) {}