package com.kukultec.reservations_service.dto.request.business;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BusinessRequest (

        @NotBlank(message = "El nombre no puede estar vacio")
        @Size(max = 150)
        String name,

        @NotBlank(message = "La direccion no puede estar vacio")
        @Size(max = 200)
        String address,

        @NotBlank(message = "El numero de telefono no puede estar vacio")
        @Pattern(regexp = "^[0-9+\\-\\s]{7,20}$")
        String telephone,

        @NotNull(message = "El status no puede estar vacio")
        Boolean active

){}
