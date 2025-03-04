package com.carlosalexis99.soccer.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JugadorDTO(Integer id,
                         @NotBlank(message = "nombre cannot be blank")
                         String nombre,
                         @NotBlank(message = "apellido cannot be blank")
                         String apellido,
                         @NotNull(message = "numero cannot be null")
                         Integer numero
) {
}
