package com.kukultec.reservations_service.repository.schedule;

import com.kukultec.reservations_service.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
