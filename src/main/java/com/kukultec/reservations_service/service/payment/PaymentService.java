package com.kukultec.reservations_service.service.payment;

import com.kukultec.reservations_service.dto.request.payment.PaymentRequest;
import com.kukultec.reservations_service.dto.response.payment.PaymentResponse;
import com.kukultec.reservations_service.entity.Appointment;
import com.kukultec.reservations_service.entity.Payment;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.payment.PaymentMapper;
import com.kukultec.reservations_service.repository.appointment.AppointmentRepository;
import com.kukultec.reservations_service.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponse save(PaymentRequest request) {
        Appointment appointment = findAppointmentOrThrow(request.appointmentId());

        Payment payment = paymentMapper.mapToEntity(request, appointment);
        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.mapToResponse(savedPayment);
    }

    public PaymentResponse getById(Long id) {
        Payment payment = findPaymentOrThrow(id);
        return paymentMapper.mapToResponse(payment);
    }

    public List<PaymentResponse> getAll() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::mapToResponse)
                .toList();
    }

    public PaymentResponse update(Long id, PaymentRequest request) {
        Payment existingPayment = findPaymentOrThrow(id);
        Appointment appointment = findAppointmentOrThrow(request.appointmentId());

        existingPayment.setAppointment(appointment);
        existingPayment.setAmount(request.amount());
        existingPayment.setMethod(request.method());
        // status y transactionRef NO se tocan aquí: los actualiza el webhook de Stripe
        // (ver PaymentService.markAsPaid(...) cuando armemos la Fase 3)

        Payment updatedPayment = paymentRepository.save(existingPayment);

        return paymentMapper.mapToResponse(updatedPayment);
    }

    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pago no encontrado");
        }
        paymentRepository.deleteById(id);
    }

    private Payment findPaymentOrThrow(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
    }

    private Appointment findAppointmentOrThrow(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
    }
}