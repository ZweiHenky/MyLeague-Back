package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Jugador;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface JugadorService {

    List<Jugador> findAll();
    List<Jugador> findAll(String nombre);
    List<Jugador> findAll(Specification specification);
    Optional<Jugador> findById(Integer id);
    Jugador save(Jugador jugador);
    void deleteById(Integer id) throws Exception;
    void deleteJugadorFromEquipo(Integer idJugador, Integer idEquipo) throws Exception;
    Jugador update(Integer id, Jugador jugador);
}
