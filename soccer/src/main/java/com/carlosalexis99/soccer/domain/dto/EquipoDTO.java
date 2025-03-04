package com.carlosalexis99.soccer.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipoDTO(Integer id,
                        @NotBlank(message = "nombre cannot be blank")
                        String nombre,
                        String logo,
                        UsuarioDTO usuario ) {
}
