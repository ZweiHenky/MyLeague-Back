package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T03:41:49-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class LigaMapperImpl implements LigaMapper {

    @Override
    public LigaDTO ligaToDto(Liga liga) {
        if ( liga == null ) {
            return null;
        }

        UsuarioDTO usuario = null;
        Integer id = null;
        String nombre = null;
        String descripcion = null;
        String direccion = null;
        String logo = null;

        usuario = mapToUsuarioDto( liga.getUsuario() );
        id = liga.getId();
        nombre = liga.getNombre();
        descripcion = liga.getDescripcion();
        direccion = liga.getDireccion();
        logo = liga.getLogo();

        LigaDTO ligaDTO = new LigaDTO( id, nombre, descripcion, direccion, logo, usuario );

        return ligaDTO;
    }

    @Override
    public Liga LigaDtoToLiga(LigaDTO ligaDTO) {
        if ( ligaDTO == null ) {
            return null;
        }

        Liga.LigaBuilder liga = Liga.builder();

        liga.id( ligaDTO.id() );
        liga.nombre( ligaDTO.nombre() );
        liga.descripcion( ligaDTO.descripcion() );
        liga.direccion( ligaDTO.direccion() );
        liga.logo( ligaDTO.logo() );
        liga.usuario( usuarioDTOToUsuario( ligaDTO.usuario() ) );

        return liga.build();
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
