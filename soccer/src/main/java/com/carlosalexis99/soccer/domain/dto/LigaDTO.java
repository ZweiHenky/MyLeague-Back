package com.carlosalexis99.soccer.domain.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;


public record LigaDTO(
                    Integer id,
                      @NotBlank(message = "nombre cannot be blank")
                      String nombre,
                      @NotBlank(message = "descripcion cannot be blank")
                      String descripcion,
                      @NotBlank(message = "direccion cannot be blank")
                      String direccion,
                      String logo,
                      UsuarioDTO usuario) {
}
