package com.kukultec.reservations_service.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequest (

        @NotNull(message = "Id de negocio requerido")
        Long businessId,

        @NotBlank(message = "Nombre completo requerido")
        @Size(max = 150)
        String fullName,

        @NotBlank(message = "Email requerido")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotNull(message = "El estado activo es obligatorio")
        Boolean active

) {}