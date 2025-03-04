package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EquipoMapper {

    EquipoMapper mapper = Mappers.getMapper(EquipoMapper.class);

    @Mapping(target = "usuario", qualifiedByName = "mapToUsuarioDto")
    EquipoDTO equipoToDto(Equipo equipo);

    // Mapeo de Usuario a UsuarioDTO
    @Named("mapToUsuarioDto")
    default UsuarioDTO mapToUsuarioDto(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail());
    }

    Equipo equipoDtoToEquipo(EquipoDTO equipoDTO);
}
