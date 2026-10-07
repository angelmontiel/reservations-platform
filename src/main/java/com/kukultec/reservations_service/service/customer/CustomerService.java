package com.kukultec.reservations_service.service.customer;

import com.kukultec.reservations_service.dto.request.customer.CustomerRequest;
import com.kukultec.reservations_service.dto.response.customer.CustomerResponse;
import com.kukultec.reservations_service.entity.Customer;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.customer.CustomerMapper;
import com.kukultec.reservations_service.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse save(CustomerRequest request) {
        Customer customer = customerMapper.mapToEntity(request);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.mapToResponse(savedCustomer);
    }

    public CustomerResponse getById(Long id) {
        Customer customer = findCustomerOrThrow(id);
        return customerMapper.mapToResponse(customer);
    }

    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToResponse)
                .toList();
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer existingCustomer = findCustomerOrThrow(id);

        existingCustomer.setFullName(request.fullName());
        existingCustomer.setEmail(request.email());
        existingCustomer.setPhone(request.phone());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.mapToResponse(updatedCustomer);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        customerRepository.deleteById(id);
    }

    private Customer findCustomerOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }
}