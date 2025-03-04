package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LigaMapper {
    LigaMapper mapper = Mappers.getMapper(LigaMapper.class);

    @Mapping(target = "usuario", qualifiedByName = "mapToUsuarioDto")
    LigaDTO ligaToDto(Liga liga);

    // Mapeo de Usuario a UsuarioDTO
    @Named("mapToUsuarioDto")
    default UsuarioDTO mapToUsuarioDto(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail());
    }
    
    Liga LigaDtoToLiga(LigaDTO ligaDTO);
}
