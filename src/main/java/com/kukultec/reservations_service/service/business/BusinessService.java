package com.kukultec.reservations_service.service.business;

import com.kukultec.reservations_service.dto.request.business.BusinessRequest;
import com.kukultec.reservations_service.dto.response.business.BusinessResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.business.BusinessMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public BusinessResponse saveBusiness(BusinessRequest request) {
        Business business = businessMapper.mapToEntity(request);
        Business savedBusiness = businessRepository.save(business);
        return businessMapper.mapToResponse(savedBusiness);
    }

    public BusinessResponse getById(Long id){
        Business business = findBusinessOrThrow(id);
        return businessMapper.mapToResponse(business);
    }

    public List<BusinessResponse> getAll(){
        return businessRepository.findAll()
                .stream()
                .map(businessMapper::mapToResponse)
                .toList();
    }

    public BusinessResponse update(Long id, BusinessRequest request){

        Business existingBusiness = findBusinessOrThrow(id);
        existingBusiness.setName(request.name());
        existingBusiness.setActive(request.active());
        existingBusiness.setAddress(request.address());
        existingBusiness.setTelephone(request.telephone());

        Business businessUpdate = businessRepository.save(existingBusiness);

        return businessMapper.mapToResponse(businessUpdate);
    }

    public void delete (Long id){

        if(!businessRepository.existsById(id)){
            throw new ResourceNotFoundException("Negocio no encontrado");
        }
        businessRepository.deleteById(id);
    }

    public Business findBusinessOrThrow(Long id){
        return businessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));

    }

}
