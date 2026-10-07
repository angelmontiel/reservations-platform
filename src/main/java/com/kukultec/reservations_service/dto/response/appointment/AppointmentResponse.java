package com.kukultec.reservations_service.dto.response.appointment;

import com.kukultec.reservations_service.enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponse (

        Long id,
        Long serviceOfferingId,
        Long employeeId,
        Long customerId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        AppointmentStatus status

) {}