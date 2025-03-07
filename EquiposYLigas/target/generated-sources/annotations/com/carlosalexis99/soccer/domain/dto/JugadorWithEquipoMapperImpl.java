package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Jugador;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T03:41:49-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class JugadorWithEquipoMapperImpl implements JugadorWithEquipoMapper {

    @Override
    public JugadorWithEquipoDTO jugadorToDto(Jugador jugador) {
        if ( jugador == null ) {
            return null;
        }

        Integer id = null;
        String nombre = null;
        List<EquipoDelJugadorDTO> equipos = null;

        id = jugador.getId();
        nombre = jugador.getNombre();
        equipos = equipoSetToEquipoDelJugadorDTOList( jugador.getEquipos() );

        JugadorWithEquipoDTO jugadorWithEquipoDTO = new JugadorWithEquipoDTO( id, nombre, equipos );

        return jugadorWithEquipoDTO;
    }

    @Override
    public Jugador jugadorDtoToJugador(JugadorWithEquipoDTO jugadorDTO) {
        if ( jugadorDTO == null ) {
            return null;
        }

        Jugador.JugadorBuilder jugador = Jugador.builder();

        jugador.id( jugadorDTO.id() );
        jugador.nombre( jugadorDTO.nombre() );
        jugador.equipos( equipoDelJugadorDTOListToEquipoSet( jugadorDTO.equipos() ) );

        return jugador.build();
    }

    protected EquipoDelJugadorDTO equipoToEquipoDelJugadorDTO(Equipo equipo) {
        if ( equipo == null ) {
            return null;
        }

        Integer id = null;
        String nombre = null;

        id = equipo.getId();
        nombre = equipo.getNombre();

        EquipoDelJugadorDTO equipoDelJugadorDTO = new EquipoDelJugadorDTO( id, nombre );

        return equipoDelJugadorDTO;
    }

    protected List<EquipoDelJugadorDTO> equipoSetToEquipoDelJugadorDTOList(Set<Equipo> set) {
        if ( set == null ) {
            return null;
        }

        List<EquipoDelJugadorDTO> list = new ArrayList<EquipoDelJugadorDTO>( set.size() );
        for ( Equipo equipo : set ) {
            list.add( equipoToEquipoDelJugadorDTO( equipo ) );
        }

        return list;
    }

    protected Equipo equipoDelJugadorDTOToEquipo(EquipoDelJugadorDTO equipoDelJugadorDTO) {
        if ( equipoDelJugadorDTO == null ) {
            return null;
        }

        Equipo.EquipoBuilder equipo = Equipo.builder();

        equipo.id( equipoDelJugadorDTO.id() );
        equipo.nombre( equipoDelJugadorDTO.nombre() );

        return equipo.build();
    }

    protected Set<Equipo> equipoDelJugadorDTOListToEquipoSet(List<EquipoDelJugadorDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Equipo> set = LinkedHashSet.newLinkedHashSet( list.size() );
        for ( EquipoDelJugadorDTO equipoDelJugadorDTO : list ) {
            set.add( equipoDelJugadorDTOToEquipo( equipoDelJugadorDTO ) );
        }

        return set;
    }
}
