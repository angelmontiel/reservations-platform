package com.kukultec.reservations_service.repository.payment;

import com.kukultec.reservations_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
