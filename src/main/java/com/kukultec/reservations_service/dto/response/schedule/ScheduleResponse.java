package com.kukultec.reservations_service.dto.response.schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ScheduleResponse (

        Long id,
        Long employeeId,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime

) {}