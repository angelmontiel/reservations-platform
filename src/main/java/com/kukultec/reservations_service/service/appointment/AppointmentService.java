package com.kukultec.reservations_service.service.appointment;

import com.kukultec.reservations_service.dto.request.appointment.AppointmentRequest;
import com.kukultec.reservations_service.dto.response.appointment.AppointmentResponse;
import com.kukultec.reservations_service.entity.*;
import com.kukultec.reservations_service.exception.BusinessRuleException;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.appointment.AppointmentMapper;
import com.kukultec.reservations_service.repository.appointment.AppointmentRepository;
import com.kukultec.reservations_service.repository.customer.CustomerRepository;
import com.kukultec.reservations_service.repository.employee.EmployeeRepository;
import com.kukultec.reservations_service.repository.serviceoffering.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentResponse save(AppointmentRequest request) {
        validateTimeRange(request);

        ServiceOffering serviceOffering = findServiceOfferingOrThrow(request.serviceOfferingId());
        Employee employee = findEmployeeOrThrow(request.employeeId());
        Customer customer = findCustomerOrThrow(request.customerId());

        validateNoOverlap(request, null);

        Appointment appointment = appointmentMapper.mapToEntity(request, serviceOffering, employee, customer);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.mapToResponse(savedAppointment);
    }

    public AppointmentResponse getById(Long id) {
        Appointment appointment = findAppointmentOrThrow(id);
        return appointmentMapper.mapToResponse(appointment);
    }

    public List<AppointmentResponse> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::mapToResponse)
                .toList();
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        validateTimeRange(request);
        Appointment existingAppointment = findAppointmentOrThrow(id);

        ServiceOffering serviceOffering = findServiceOfferingOrThrow(request.serviceOfferingId());
        Employee employee = findEmployeeOrThrow(request.employeeId());
        Customer customer = findCustomerOrThrow(request.customerId());

        validateNoOverlap(request, id); // excluye la cita actual de la comparación

        existingAppointment.setService(serviceOffering);
        existingAppointment.setEmployee(employee);
        existingAppointment.setCustomer(customer);
        existingAppointment.setDate(request.date());
        existingAppointment.setStartTime(request.startTime());
        existingAppointment.setEndTime(request.endTime());
        // status NO se toca aquí: cambia por endpoints dedicados
        // (confirm/cancel/complete), no junto con editar los demás campos.

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);

        return appointmentMapper.mapToResponse(updatedAppointment);
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cita no encontrada");
        }
        appointmentRepository.deleteById(id);
    }

    // -----------------------------------------------------------------
    // Validaciones de negocio
    // -----------------------------------------------------------------

    private void validateTimeRange(AppointmentRequest request) {
        if (!request.startTime().isBefore(request.endTime())) {
            throw new BusinessRuleException("La hora de inicio debe ser anterior a la hora de fin");
        }
    }

    private void validateNoOverlap(AppointmentRequest request, Long excludeAppointmentId) {
        boolean overlaps = appointmentRepository.existsOverlappingAppointment(
                request.employeeId(),
                request.date(),
                request.startTime(),
                request.endTime(),
                excludeAppointmentId != null ? excludeAppointmentId : -1L
        );

        if (overlaps) {
            throw new BusinessRuleException("El empleado ya tiene una cita en ese horario");
        }
    }

    // -----------------------------------------------------------------
    // Resolución de entidades relacionadas
    // -----------------------------------------------------------------

    private Appointment findAppointmentOrThrow(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
    }

    private ServiceOffering findServiceOfferingOrThrow(Long id) {
        return serviceOfferingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));
    }

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));
    }

    private Customer findCustomerOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }
}