package com.kukultec.reservations_service.mapper.serviceoffering;

import com.kukultec.reservations_service.dto.request.serviceofferingservice.ServiceOfferingRequest;
import com.kukultec.reservations_service.dto.response.serviceofferingservice.ServiceOfferingResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.ServiceOffering;
import org.springframework.stereotype.Component;

@Component
public class ServiceOfferingMapper {

    public ServiceOffering mapToEntity (ServiceOfferingRequest request, Business business){

        return ServiceOffering.builder()
                .business(business)
                .name(request.name())
                .durationMinutes(request.durationMinutes())
                .price(request.price())
                .build();
    }

    public ServiceOfferingResponse mapToResponse(ServiceOffering serviceOffering) {
        return new ServiceOfferingResponse(
                serviceOffering.getId(),
                serviceOffering.getBusiness().getId(),
                serviceOffering.getName(),
                serviceOffering.getDurationMinutes(),
                serviceOffering.getPrice()
        );
    }
}
