package com.kukultec.reservations_service.service.schedule;

import com.kukultec.reservations_service.dto.request.schedule.ScheduleRequest;
import com.kukultec.reservations_service.dto.response.schedule.ScheduleResponse;
import com.kukultec.reservations_service.entity.Employee;
import com.kukultec.reservations_service.entity.Schedule;
import com.kukultec.reservations_service.exception.BusinessRuleException;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.schedule.ScheduleMapper;
import com.kukultec.reservations_service.repository.employee.EmployeeRepository;
import com.kukultec.reservations_service.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleResponse save(ScheduleRequest request) {
        validateTimeRange(request);
        Employee employee = findEmployeeOrThrow(request.employeeId());

        Schedule schedule = scheduleMapper.mapToEntity(request, employee);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.mapToResponse(savedSchedule);
    }

    public ScheduleResponse getById(Long id) {
        Schedule schedule = findScheduleOrThrow(id);
        return scheduleMapper.mapToResponse(schedule);
    }

    public List<ScheduleResponse> getAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::mapToResponse)
                .toList();
    }

    public ScheduleResponse update(Long id, ScheduleRequest request) {
        validateTimeRange(request);
        Schedule existingSchedule = findScheduleOrThrow(id);
        Employee employee = findEmployeeOrThrow(request.employeeId());

        existingSchedule.setEmployee(employee);
        existingSchedule.setDayOfWeek(request.dayOfWeek());
        existingSchedule.setStartTime(request.startTime());
        existingSchedule.setEndTime(request.endTime());

        Schedule updatedSchedule = scheduleRepository.save(existingSchedule);

        return scheduleMapper.mapToResponse(updatedSchedule);
    }

    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Horario no encontrado");
        }
        scheduleRepository.deleteById(id);
    }

    private void validateTimeRange(ScheduleRequest request) {
        if (!request.startTime().isBefore(request.endTime())) {
            throw new BusinessRuleException("La hora de inicio debe ser anterior a la hora de fin");
        }
    }

    private Schedule findScheduleOrThrow(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado"));
    }

    private Employee findEmployeeOrThrow(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));
    }
}