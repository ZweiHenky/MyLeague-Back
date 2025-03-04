package com.carlosalexis99.soccer.domain.dto;

import java.util.List;

public record JugadorWithEquipoDTO(
        Integer id,
        String nombre,
        List<EquipoDelJugadorDTO> equipos) {
}
