package com.kukultec.reservations_service.service.business;

import com.kukultec.reservations_service.dto.request.business.SaveBusinessRequest;
import com.kukultec.reservations_service.dto.response.business.BusinessResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.mapper.business.BusinessMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public BusinessResponse saveBusiness(SaveBusinessRequest request) {
        Business business = businessMapper.mapToEntity(request);
        Business savedBusiness = businessRepository.save(business);
        return businessMapper.mapToResponse(savedBusiness);
    }

}
