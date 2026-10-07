package com.kukultec.reservations_service.mapper.appointment;

import com.kukultec.reservations_service.dto.request.appointment.AppointmentRequest;
import com.kukultec.reservations_service.dto.response.appointment.AppointmentResponse;
import com.kukultec.reservations_service.entity.Appointment;
import com.kukultec.reservations_service.entity.Customer;
import com.kukultec.reservations_service.entity.Employee;
import com.kukultec.reservations_service.entity.ServiceOffering;
import com.kukultec.reservations_service.enums.AppointmentStatus;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment mapToEntity(
            AppointmentRequest request,
            ServiceOffering serviceOffering,
            Employee employee,
            Customer customer
    ) {
        return Appointment.builder()
                .service(serviceOffering)
                .employee(employee)
                .customer(customer)
                .date(request.date())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .status(AppointmentStatus.PENDING)
                .build();
    }

    public AppointmentResponse mapToResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getService().getId(),
                appointment.getEmployee().getId(),
                appointment.getCustomer().getId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus()
        );
    }
}