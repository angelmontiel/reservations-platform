package com.kukultec.reservations_service.repository.serviceoffering;

import com.kukultec.reservations_service.entity.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {

    List<ServiceOffering> findByBusinessId(Long id);

    boolean existsByBusinessIdAndNameIgnoreCase (Long id, String name);

    boolean existsByBusinessIdAndNameIgnoreCaseAndIdNot(Long businessId, String name, Long id);
}
