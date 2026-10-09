package com.kukultec.reservations_service.controller.serviceoffering;

import com.kukultec.reservations_service.dto.request.serviceofferingservice.ServiceOfferingRequest;
import com.kukultec.reservations_service.dto.response.serviceofferingservice.ServiceOfferingResponse;
import com.kukultec.reservations_service.service.serviceoffering.ServiceOfferingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOfferingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceOfferingService.getById(id));
    }

    // GET /service-offerings              -> todos
    // GET /service-offerings?businessId=1 -> solo los de un negocio (filtro opcional)
    @GetMapping
    public ResponseEntity<List<ServiceOfferingResponse>> getAll(
            @RequestParam(required = false) Long businessId) {
        return ResponseEntity.ok(serviceOfferingService.getAll(businessId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOfferingResponse> update(@PathVariable Long id,
                                                          @Valid @RequestBody ServiceOfferingRequest request) {
        return ResponseEntity.ok(serviceOfferingService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceOfferingService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
