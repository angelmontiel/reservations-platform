package com.kukultec.reservations_service.mapper.business;

import com.kukultec.reservations_service.dto.request.business.BusinessRequest;
import com.kukultec.reservations_service.dto.response.business.BusinessResponse;
import com.kukultec.reservations_service.entity.Business;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {

    public Business mapToEntity(BusinessRequest request) {
        return Business.builder()
                .name(request.name())
                .address(request.address())
                .telephone(request.telephone())
                .active(request.active())
                .build();
    }

    public BusinessResponse mapToResponse(Business business) {

        return new BusinessResponse(
                business.getId(),
                business.getName(),
                business.getAddress(),
                business.getTelephone(),
                business.getActive()
        );
    }
}
