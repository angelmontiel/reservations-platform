package com.kukultec.reservations_service.mapper.employee;

import com.kukultec.reservations_service.dto.request.employee.EmployeeRequest;
import com.kukultec.reservations_service.dto.response.employee.EmployeeResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee mapToEntity(EmployeeRequest request, Business business) {
        return Employee.builder()
                .business(business)
                .fullName(request.fullName())
                .email(request.email())
                .active(request.active())
                .build();
    }

    public EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getBusiness().getId(),
                employee.getFullName(),
                employee.getEmail(),
                employee.getActive()
        );
    }
}