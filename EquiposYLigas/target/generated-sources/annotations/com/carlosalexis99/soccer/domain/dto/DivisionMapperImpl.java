package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Division;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T03:41:49-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class DivisionMapperImpl implements DivisionMapper {

    @Override
    public DivisionDTO divisionToDto(Division division) {
        if ( division == null ) {
            return null;
        }

        Integer id = null;
        String nombre = null;
        String descripcion = null;
        Integer edad_minima = null;
        Integer edad_maxima = null;
        BigDecimal premio = null;
        BigDecimal arbitraje = null;
        LigaDTO liga = null;

        id = division.getId();
        nombre = division.getNombre();
        descripcion = division.getDescripcion();
        edad_minima = division.getEdad_minima();
        edad_maxima = division.getEdad_maxima();
        premio = division.getPremio();
        arbitraje = division.getArbitraje();
        liga = ligaToLigaDTO( division.getLiga() );

        DivisionDTO divisionDTO = new DivisionDTO( id, nombre, descripcion, edad_minima, edad_maxima, premio, arbitraje, liga );

        return divisionDTO;
    }

    @Override
    public Division dtoToDivision(DivisionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Division.DivisionBuilder division = Division.builder();

        division.id( dto.id() );
        division.nombre( dto.nombre() );
        division.descripcion( dto.descripcion() );
        division.edad_minima( dto.edad_minima() );
        division.edad_maxima( dto.edad_maxima() );
        division.premio( dto.premio() );
        division.arbitraje( dto.arbitraje() );
        division.liga( ligaDTOToLiga( dto.liga() ) );

        return division.build();
    }

    protected UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
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

    protected LigaDTO ligaToLigaDTO(Liga liga) {
        if ( liga == null ) {
            return null;
        }

        Integer id = null;
        String nombre = null;
        String descripcion = null;
        String direccion = null;
        String logo = null;
        UsuarioDTO usuario = null;

        id = liga.getId();
        nombre = liga.getNombre();
        descripcion = liga.getDescripcion();
        direccion = liga.getDireccion();
        logo = liga.getLogo();
        usuario = usuarioToUsuarioDTO( liga.getUsuario() );

        LigaDTO ligaDTO = new LigaDTO( id, nombre, descripcion, direccion, logo, usuario );

        return ligaDTO;
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

    protected Liga ligaDTOToLiga(LigaDTO ligaDTO) {
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
}
