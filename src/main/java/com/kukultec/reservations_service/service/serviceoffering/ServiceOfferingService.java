package com.kukultec.reservations_service.service.serviceoffering;

import com.kukultec.reservations_service.dto.request.serviceofferingservice.ServiceOfferingRequest;
import com.kukultec.reservations_service.dto.response.serviceofferingservice.ServiceOfferingResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.ServiceOffering;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.serviceoffering.ServiceOfferingMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import com.kukultec.reservations_service.repository.serviceoffering.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ServiceOfferingMapper serviceOfferingMapper;
    private final BusinessRepository businessRepository;




    public ServiceOfferingResponse saveServiceOffering (ServiceOfferingRequest request){

        Business business = businessRepository.findById(request.businessId())
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));

        ServiceOffering serviceOffering = serviceOfferingMapper.mapToEntity(request, business);
        ServiceOffering savedEntity = serviceOfferingRepository.save(serviceOffering);

        return serviceOfferingMapper.mapToResponse(savedEntity);

    }


}
