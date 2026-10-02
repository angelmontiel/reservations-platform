package com.kukultec.reservations_service.mapper.schedule;

import com.kukultec.reservations_service.dto.request.schedule.ScheduleRequest;
import com.kukultec.reservations_service.dto.response.schedule.ScheduleResponse;
import com.kukultec.reservations_service.entity.Employee;
import com.kukultec.reservations_service.entity.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public Schedule mapToEntity(ScheduleRequest request, Employee employee) {
        return Schedule.builder()
                .employee(employee)
                .dayOfWeek(request.dayOfWeek())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .build();
    }

    public ScheduleResponse mapToResponse(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getEmployee().getId(),
                schedule.getDayOfWeek(),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
    }
}