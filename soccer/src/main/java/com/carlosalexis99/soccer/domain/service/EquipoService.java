package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

    List<Equipo> findAll();
    List<Equipo> findAll(String nombre);
    List<Equipo> findAll(Specification specification);
    Optional<Equipo> findById(Integer id);
    Equipo save(Equipo equipo);
    void deleteById(Integer id) throws Exception;
    void deleteEquipoFromDivision(Integer idEquipo, Integer idLiga) throws Exception;
    Equipo update(Integer id, Equipo equipo);
    List<Equipo> getEquipoByUsuarioId(Integer id);
    Equipo addJugadorToEquipo(Integer equipoId, Integer jugadorId);
}
