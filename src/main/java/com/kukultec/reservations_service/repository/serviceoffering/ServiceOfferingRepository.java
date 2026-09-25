package com.kukultec.reservations_service.repository.serviceoffering;

import com.kukultec.reservations_service.entity.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {
}
