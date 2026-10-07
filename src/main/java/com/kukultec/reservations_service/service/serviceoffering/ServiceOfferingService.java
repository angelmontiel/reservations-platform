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

        ServiceOffering serviceOffering = serviceOfferingMapper.mapToEntity(request, business);
        ServiceOffering savedEntity = serviceOfferingRepository.save(serviceOffering);

        return serviceOfferingMapper.mapToResponse(savedEntity);

    }

    public ServiceOfferingResponse getById(Long id){

        ServiceOffering serviceOffering = findServiceOfferingOrThrow(id);
        return serviceOfferingMapper.mapToResponse(serviceOffering);
    }

    public List<ServiceOfferingResponse> getAll (){
        return serviceOfferingRepository.findAll()
                .stream()
                .map(serviceOfferingMapper::mapToResponse)
                .toList();
    }

    public ServiceOfferingResponse update (Long id, ServiceOfferingRequest request){

        ServiceOffering existsServiceoffering = findServiceOfferingOrThrow(id);

        Business business = findBusinessOrThrow(request.businessId());

        existsServiceoffering.setName(request.name());
        existsServiceoffering.setDurationMinutes(request.durationMinutes());
        existsServiceoffering.setPrice(request.price());
        existsServiceoffering.setBusiness(business);

        ServiceOffering serviceOfferingUpdate = serviceOfferingRepository.save(existsServiceoffering);

        return serviceOfferingMapper.mapToResponse(serviceOfferingUpdate);

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
