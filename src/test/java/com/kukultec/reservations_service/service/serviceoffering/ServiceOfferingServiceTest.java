package com.kukultec.reservations_service.service.serviceoffering;

import com.kukultec.reservations_service.dto.request.serviceofferingservice.ServiceOfferingRequest;
import com.kukultec.reservations_service.dto.response.serviceofferingservice.ServiceOfferingResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.ServiceOffering;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.serviceoffering.ServiceOfferingMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import com.kukultec.reservations_service.repository.serviceoffering.ServiceOfferingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ServiceOfferingServiceTest {

    @Mock
    private ServiceOfferingRepository serviceOfferingRepository;

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private ServiceOfferingMapper serviceOfferingMapper;

    @InjectMocks
    private ServiceOfferingService serviceOfferingService;

    @Test
    void shouldSaveServiceOfferingWhenBusinessExists() {

        // arrange
        ServiceOfferingRequest request = new ServiceOfferingRequest(
                1L, "Corte de cabello", 30, new BigDecimal("150.00"));

        Business business = Business.builder().id(1L).name("Barbería El Corte").build();
        ServiceOffering entity = ServiceOffering.builder().business(business).name("Corte de cabello").build();
        ServiceOffering savedEntity = ServiceOffering.builder().id(1L).business(business).name("Corte de cabello").build();

        ServiceOfferingResponse expectedResponse = new ServiceOfferingResponse(
                1L, 1L, "Corte de cabello", 30, new BigDecimal("150.00"));   // 👈 esta línea es la que faltaba

        when(businessRepository.findById(1L)).thenReturn(Optional.of(business));
        when(serviceOfferingMapper.mapToEntity(request, business)).thenReturn(entity);
        when(serviceOfferingRepository.save(entity)).thenReturn(savedEntity);
        when(serviceOfferingMapper.mapToResponse(savedEntity)).thenReturn(expectedResponse);

        // act
        var response = serviceOfferingService.saveServiceOffering(request);

        // assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expectedResponse);   // ya que la tienes, aprovecha para comparar
    }

    @Test
    void shouldThrowExceptionWhenBusinessDoesNotExist() {
        ServiceOfferingRequest request = new ServiceOfferingRequest(
                99L, "Corte de cabello", 30, new BigDecimal("150.00"));

        when(businessRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> serviceOfferingService.saveServiceOffering(request));
    }


}