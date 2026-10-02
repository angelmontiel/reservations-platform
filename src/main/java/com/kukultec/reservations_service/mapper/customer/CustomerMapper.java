package com.kukultec.reservations_service.mapper.customer;

import com.kukultec.reservations_service.dto.request.customer.CustomerRequest;
import com.kukultec.reservations_service.dto.response.customer.CustomerResponse;
import com.kukultec.reservations_service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToEntity(CustomerRequest request) {
        return Customer.builder()
                .fullName(request.fullName())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

    public CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}