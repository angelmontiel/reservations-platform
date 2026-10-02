package com.kukultec.reservations_service.controller;

import com.kukultec.reservations_service.dto.request.business.BusinessRequest;
import com.kukultec.reservations_service.dto.response.business.BusinessResponse;
import com.kukultec.reservations_service.service.business.BusinessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping("")
    public ResponseEntity<BusinessResponse> save(@Valid @RequestBody BusinessRequest request) {
        BusinessResponse response = businessService.saveBusiness(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
