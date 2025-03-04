package com.carlosalexis99.soccer.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(Integer id,
                         @NotBlank(message = "nombre cannot be blank")
                         String nombre,
                         @NotBlank(message = "apellido cannot be blank")
                         String apellido,
                         @NotBlank(message = "telefono cannot be blank")
                         String telefono,
                         @Email
                         @NotBlank(message = "email cannot be blank")
                         String email) {
}
