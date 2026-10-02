package com.kukultec.reservations_service.dto.response.payment;

import com.kukultec.reservations_service.enums.PaymentStatus;

import java.math.BigDecimal;

public record PaymentResponse (

        Long id,
        Long appointmentId,
        BigDecimal amount,
        PaymentStatus status,
        String method,
        String transactionRef

) {}