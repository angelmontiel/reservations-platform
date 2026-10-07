package com.kukultec.reservations_service.repository.appointment;

import com.kukultec.reservations_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * true si el empleado ya tiene una cita (no cancelada) ese día cuyo rango
     * de horario se traslapa con [startTime, endTime).
     * excludeAppointmentId se usa en update (para no comparar la cita contra sí misma);
     * en creación, pasa -1L o null manejado aparte.
     */
    @Query("""
            SELECT COUNT(a) > 0 FROM Appointment a
            WHERE a.employee.id = :employeeId
            AND a.date = :date
            AND a.status <> com.kukultec.reservations_service.enums.AppointmentStatus.CANCELLED
            AND a.id <> :excludeAppointmentId
            AND (a.startTime < :endTime AND a.endTime > :startTime)
            """)
    boolean existsOverlappingAppointment(
            @Param("employeeId") Long employeeId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("excludeAppointmentId") Long excludeAppointmentId
    );
}