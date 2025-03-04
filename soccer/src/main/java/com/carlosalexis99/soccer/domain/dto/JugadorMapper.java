package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Jugador;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JugadorMapper {

    JugadorMapper mapper = Mappers.getMapper(JugadorMapper.class);

    JugadorDTO jugadorToDto(Jugador jugador);

    Jugador jugadorDtoToJugador(JugadorDTO jugadorDTO);
}
