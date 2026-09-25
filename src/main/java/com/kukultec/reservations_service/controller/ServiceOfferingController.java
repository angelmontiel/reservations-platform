package com.kukultec.reservations_service.controller;

import com.kukultec.reservations_service.dto.request.serviceofferingservice.ServiceOfferingRequest;
import com.kukultec.reservations_service.dto.response.serviceofferingservice.ServiceOfferingResponse;
import com.kukultec.reservations_service.service.serviceoffering.ServiceOfferingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-offerings")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;



    @PostMapping("")
    public ResponseEntity<ServiceOfferingResponse>saveServiceOffering (@Valid @RequestBody ServiceOfferingRequest request){
        ServiceOfferingResponse response = serviceOfferingService.saveServiceOffering(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
