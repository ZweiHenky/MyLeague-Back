package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Usuario;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T03:41:49-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO usuarioToUsuarioDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Integer id = null;
        String nombre = null;
        String apellido = null;
        String telefono = null;
        String email = null;

        id = usuario.getId();
        nombre = usuario.getNombre();
        apellido = usuario.getApellido();
        telefono = usuario.getTelefono();
        email = usuario.getEmail();

        UsuarioDTO usuarioDTO = new UsuarioDTO( id, nombre, apellido, telefono, email );

        return usuarioDTO;
    }

    @Override
    public Usuario usuarioDtoToUsuario(LigaDTO ligaDTO) {
        if ( ligaDTO == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.id( ligaDTO.id() );
        usuario.nombre( ligaDTO.nombre() );

        return usuario.build();
    }
}
