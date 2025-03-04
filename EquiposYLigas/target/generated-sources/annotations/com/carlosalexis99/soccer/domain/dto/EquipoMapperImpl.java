package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T03:41:49-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class EquipoMapperImpl implements EquipoMapper {

    @Override
    public EquipoDTO equipoToDto(Equipo equipo) {
        if ( equipo == null ) {
            return null;
        }

        UsuarioDTO usuario = null;
        Integer id = null;
        String nombre = null;
        String logo = null;

        usuario = mapToUsuarioDto( equipo.getUsuario() );
        id = equipo.getId();
        nombre = equipo.getNombre();
        logo = equipo.getLogo();

        EquipoDTO equipoDTO = new EquipoDTO( id, nombre, logo, usuario );

        return equipoDTO;
    }

    @Override
    public Equipo equipoDtoToEquipo(EquipoDTO equipoDTO) {
        if ( equipoDTO == null ) {
            return null;
        }

        Equipo.EquipoBuilder equipo = Equipo.builder();

        equipo.id( equipoDTO.id() );
        equipo.nombre( equipoDTO.nombre() );
        equipo.logo( equipoDTO.logo() );
        equipo.usuario( usuarioDTOToUsuario( equipoDTO.usuario() ) );

        return equipo.build();
    }

    protected Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.id( usuarioDTO.id() );
        usuario.nombre( usuarioDTO.nombre() );
        usuario.apellido( usuarioDTO.apellido() );
        usuario.telefono( usuarioDTO.telefono() );
        usuario.email( usuarioDTO.email() );

        return usuario.build();
    }
}
