package com.kukultec.reservations_service.exception;

/**
 * Se lanza cuando un dato es válido en forma (pasó Bean Validation),
 * pero viola una regla de negocio: traslape de horario, hora de inicio
 * posterior a la de fin, un negocio duplicado, etc.
 *
 * El GlobalExceptionHandler la mapea a 409 CONFLICT (no 400 BAD_REQUEST),
 * porque el dato en sí es correcto, solo choca con el estado actual
 * del sistema.
 */
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }
}