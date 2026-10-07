package com.kukultec.reservations_service.service.employee;

import com.kukultec.reservations_service.dto.request.employee.EmployeeRequest;
import com.kukultec.reservations_service.dto.response.employee.EmployeeResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.Employee;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.employee.EmployeeMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import com.kukultec.reservations_service.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BusinessRepository businessRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse save(EmployeeRequest request) {
        Business business = findBusinessOrThrow(request.businessId());

        Employee employee = employeeMapper.mapToEntity(request, business);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToResponse(savedEmployee);
    }

    public EmployeeResponse getById(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        return employeeMapper.mapToResponse(employee);
    }

    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::mapToResponse)
                .toList();
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee existingEmployee = findEmployeeOrThrow(id);
        Business business = findBusinessOrThrow(request.businessId());

        existingEmployee.setBusiness(business);
        existingEmployee.setFullName(request.fullName());
        existingEmployee.setEmail(request.email());
        existingEmployee.setActive(request.active());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.mapToResponse(updatedEmployee);
    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empleado no encontrado");
        }
        employeeRepository.deleteById(id);
    }

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));
    }

    private Business findBusinessOrThrow(Long businessId) {
        return businessRepository.findById(businessId)
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));
    }
}