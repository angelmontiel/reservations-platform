package com.kukultec.reservations_service.repository.employee;

import com.kukultec.reservations_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
