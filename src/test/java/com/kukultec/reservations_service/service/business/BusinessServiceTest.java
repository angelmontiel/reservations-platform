package com.kukultec.reservations_service.service.business;

import com.kukultec.reservations_service.dto.request.business.BusinessRequest;
import com.kukultec.reservations_service.dto.response.business.BusinessResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.mapper.business.BusinessMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private BusinessMapper businessMapper;

    @InjectMocks
    private BusinessService businessService;

    @Test
    void saveBusiness_shouldReturnResponse_whenNameIsNotDuplicated() {
        // given
        BusinessRequest request = new BusinessRequest(
                "Consultorio Medico", "Calle 19 de noviembre", "2463331282", true);

        Business entity = Business.builder()
                .name("Consultorio Medico")
                .address("Calle 19 de noviembre")
                .telephone("2463331282")
                .active(true)
                .build();

        Business savedEntity = Business.builder()
                .id(1L)
                .name("Consultorio Medico")
                .address("Calle 19 de noviembre")
                .telephone("2463331282")
                .active(true)
                .build();

        BusinessResponse expectedResponse = new BusinessResponse(
                1L, "Consultorio Medico", "Calle 19 de noviembre", "2463331282", true);

        when(businessMapper.mapToEntity(request)).thenReturn(entity);
        when(businessRepository.save(entity)).thenReturn(savedEntity);
        when(businessMapper.mapToResponse(savedEntity)).thenReturn(expectedResponse);

        BusinessResponse result = businessService.saveBusiness(request);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResponse);
        verify(businessRepository).save(entity);
    }

    @Test
    void getAll_shouldReturnAllBusinessesMappedToResponse() {
        // given
        Business business1 = Business.builder()
                .id(1L).name("Consultorio Medico").address("Calle 19 de noviembre")
                .telephone("2463331282").active(true).build();

        Business business2 = Business.builder()
                .id(2L).name("Barberia Central").address("Av. Juarez 100")
                .telephone("2461112233").active(true).build();

        BusinessResponse response1 = new BusinessResponse(
                1L, "Consultorio Medico", "Calle 19 de noviembre", "2463331282", true);
        BusinessResponse response2 = new BusinessResponse(
                2L, "Barberia Central", "Av. Juarez 100", "2461112233", true);

        when(businessRepository.findAll()).thenReturn(List.of(business1, business2));
        when(businessMapper.mapToResponse(business1)).thenReturn(response1);
        when(businessMapper.mapToResponse(business2)).thenReturn(response2);

        // when
        List<BusinessResponse> result = businessService.getAll();

        // then
        assertThat(result).containsExactly(response1, response2);
        verify(businessRepository).findAll();
    }


}