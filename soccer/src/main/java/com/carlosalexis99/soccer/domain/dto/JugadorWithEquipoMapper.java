package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Jugador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface JugadorWithEquipoMapper {

    JugadorWithEquipoMapper mapper = Mappers.getMapper(JugadorWithEquipoMapper.class);

    //target indica el campo en el DTO en el cual se debe mapear el valor.
    //source indica el campo de la clase de origen (Jugador) desde donde se tomará el valor.
    //En este caso Mapping requiere de un metodo personalizado porque tiene diferente tipo de dato equipos en el DTO y en Jugador por lo que podría omitirse
    //@Mapping(target = "equipos", source = "equipos")
    JugadorWithEquipoDTO jugadorToDto(Jugador jugador);

    //MapStruct infiere el metodo a utilizar
    default List<EquipoDelJugadorDTO> mapToEquipoDto(List<Equipo> equipos) {
        return equipos.stream()
                .map(equipo -> new EquipoDelJugadorDTO(equipo.getId(), equipo.getNombre()))
                .collect(Collectors.toList());
    }

    Jugador jugadorDtoToJugador(JugadorWithEquipoDTO jugadorDTO);
}
