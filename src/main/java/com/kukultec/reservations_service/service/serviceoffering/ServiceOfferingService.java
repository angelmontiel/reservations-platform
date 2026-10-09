package com.kukultec.reservations_service.service.serviceoffering;

import com.kukultec.reservations_service.dto.request.serviceofferingservice.ServiceOfferingRequest;
import com.kukultec.reservations_service.dto.response.serviceofferingservice.ServiceOfferingResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.ServiceOffering;
import com.kukultec.reservations_service.exception.BusinessRuleException;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.serviceoffering.ServiceOfferingMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import com.kukultec.reservations_service.repository.serviceoffering.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ServiceOfferingMapper serviceOfferingMapper;
    private final BusinessRepository businessRepository;




    public ServiceOfferingResponse saveServiceOffering (ServiceOfferingRequest request){

        Business business = businessRepository.findById(request.businessId())
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));

        if (serviceOfferingRepository.existsByBusinessIdAndNameIgnoreCase(business.getId(), request.name())) {
            throw new BusinessRuleException("El negocio ya tiene un servicio con ese nombre");
        }

        ServiceOffering serviceOffering = serviceOfferingMapper.mapToEntity(request, business);
        ServiceOffering savedEntity = serviceOfferingRepository.save(serviceOffering);

        return serviceOfferingMapper.mapToResponse(savedEntity);

    }

    public ServiceOfferingResponse getById(Long id){

        ServiceOffering serviceOffering = findServiceOfferingOrThrow(id);
        return serviceOfferingMapper.mapToResponse(serviceOffering);
    }

    public List<ServiceOfferingResponse> getAll(Long businessId) {
        List<ServiceOffering> offerings = (businessId == null)
                ? serviceOfferingRepository.findAll()
                : serviceOfferingRepository.findByBusinessId(businessId);

        return offerings.stream()
                .map(serviceOfferingMapper::mapToResponse)
                .toList();
    }

    public ServiceOfferingResponse update(Long id, ServiceOfferingRequest request) {
        ServiceOffering existing = findServiceOfferingOrThrow(id);
        Business business = findBusinessOrThrow(request.businessId());

        if (serviceOfferingRepository.existsByBusinessIdAndNameIgnoreCaseAndIdNot(
                business.getId(), request.name(), id)) {
            throw new BusinessRuleException("El negocio ya tiene otro servicio con ese nombre");
        }

        existing.setBusiness(business);
        existing.setName(request.name());
        existing.setDurationMinutes(request.durationMinutes());
        existing.setPrice(request.price());

        ServiceOffering updated = serviceOfferingRepository.save(existing);

        return serviceOfferingMapper.mapToResponse(updated);
    }


    public void delete (Long id){
        if(!serviceOfferingRepository.existsById(id)){
            throw new ResourceNotFoundException("Servicio no encontrado");
        }
        serviceOfferingRepository.deleteById(id);
    }

    public Business findBusinessOrThrow (Long id){
        return businessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));

    }

    public ServiceOffering findServiceOfferingOrThrow(Long id){
        return serviceOfferingRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Servicio no encontrado"));
    }
}
