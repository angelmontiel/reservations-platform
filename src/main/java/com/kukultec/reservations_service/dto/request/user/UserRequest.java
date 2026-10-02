package com.kukultec.reservations_service.dto.request.user;

import com.kukultec.reservations_service.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest (

        // Opcional: solo aplica a ADMIN/EMPLOYEE, un CUSTOMER puede no tener negocio
        Long businessId,

        @NotBlank(message = "Username requerido")
        @Size(max = 100)
        String username,

        @NotBlank(message = "Email requerido")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "Contraseña requerida")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password, // texto plano: el Service la hashea antes de guardar, nunca se persiste así

        @NotNull(message = "Rol requerido")
        UserRole role

) {}