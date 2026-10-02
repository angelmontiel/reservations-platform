package com.kukultec.reservations_service.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest (

        @NotBlank(message = "Nombre completo requerido")
        @Size(max = 150)
        String fullName,

        @NotBlank(message = "Email requerido")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "Numero de telefono requerido")
        @Pattern(regexp = "^[0-9+\\-\\s]{7,20}$", message = "Formato de teléfono inválido")
        String phone

) {}