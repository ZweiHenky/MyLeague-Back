package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Jugador;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T03:41:49-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class JugadorMapperImpl implements JugadorMapper {

    @Override
    public JugadorDTO jugadorToDto(Jugador jugador) {
        if ( jugador == null ) {
            return null;
        }

        Integer id = null;
        String nombre = null;
        String apellido = null;
        Integer numero = null;

        id = jugador.getId();
        nombre = jugador.getNombre();
        apellido = jugador.getApellido();
        numero = jugador.getNumero();

        JugadorDTO jugadorDTO = new JugadorDTO( id, nombre, apellido, numero );

        return jugadorDTO;
    }

    @Override
    public Jugador jugadorDtoToJugador(JugadorDTO jugadorDTO) {
        if ( jugadorDTO == null ) {
            return null;
        }

        Jugador.JugadorBuilder jugador = Jugador.builder();

        jugador.id( jugadorDTO.id() );
        jugador.nombre( jugadorDTO.nombre() );
        jugador.apellido( jugadorDTO.apellido() );
        jugador.numero( jugadorDTO.numero() );

        return jugador.build();
    }
}
