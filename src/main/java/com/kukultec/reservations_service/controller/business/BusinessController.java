package com.kukultec.reservations_service.controller.business;

import com.kukultec.reservations_service.dto.request.business.BusinessRequest;
import com.kukultec.reservations_service.dto.response.business.BusinessResponse;
import com.kukultec.reservations_service.service.business.BusinessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<BusinessResponse> getById(@PathVariable Long id){
        BusinessResponse response = businessService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<BusinessResponse>> getAll(){
        List<BusinessResponse> responses = businessService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PutMapping("")
    public ResponseEntity<BusinessResponse> update (@PathVariable Long id,
                                                    @Valid @RequestBody BusinessRequest request){
        BusinessResponse response = businessService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id){
        businessService.delete(id);
        return ResponseEntity.noContent().build();
    }






}




