package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;


public record DivisionDTO(
        Integer id,
        @NotBlank(message = "nombre cannot be blank")
        String nombre,
        @NotBlank(message = "descripcion cannot be blank")
        String descripcion,
        Integer edad_minima,
        Integer edad_maxima,
        BigDecimal premio,
        BigDecimal arbitraje,
        LigaDTO liga
) {
}
