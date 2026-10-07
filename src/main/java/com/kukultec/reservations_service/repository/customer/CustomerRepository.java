package com.kukultec.reservations_service.repository.customer;

import com.kukultec.reservations_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
