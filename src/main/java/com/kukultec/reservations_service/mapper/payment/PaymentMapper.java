package com.kukultec.reservations_service.mapper.payment;

import com.kukultec.reservations_service.dto.request.payment.PaymentRequest;
import com.kukultec.reservations_service.dto.response.payment.PaymentResponse;
import com.kukultec.reservations_service.entity.Appointment;
import com.kukultec.reservations_service.entity.Payment;
import com.kukultec.reservations_service.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment mapToEntity(PaymentRequest request, Appointment appointment) {
        return Payment.builder()
                .appointment(appointment)
                .amount(request.amount())
                .method(request.method())
                .status(PaymentStatus.PENDING)
                .transactionRef(null)
                .build();
    }

    public PaymentResponse mapToResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAppointment().getId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getTransactionRef()
        );
    }
}