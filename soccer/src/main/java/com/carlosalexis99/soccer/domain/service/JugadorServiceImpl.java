package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Jugador;
import com.carlosalexis99.soccer.persistence.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    //v1 no usado
    @Override
    public List<Jugador> findAll() {
        return (List<Jugador>) jugadorRepository.findAll();
    }

    //v2 no usado
    @Override
    public List<Jugador> findAll(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            return (List<Jugador>) jugadorRepository.findAll();
        }
        String nombreToLower = nombre.toLowerCase();
        return (List<Jugador>) jugadorRepository.findAllByNombreContaining(nombreToLower);
    }

    //v3 en uso
    @Override
    public List<Jugador> findAll(Specification specification) {
        return (List<Jugador>) jugadorRepository.findAll(specification);
    }

    @Override
    public Optional<Jugador> findById(Integer id) {
        return jugadorRepository.findById(id);
    }

    @Override
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new Exception("Jugador not found"));
        jugador.getEquipos().forEach(equipo -> equipo.getJugadores().remove(jugador));
        jugador.getEquipos().clear();
        jugadorRepository.save(jugador);
        jugadorRepository.delete(jugador);
    }

    @Override
    public void deleteJugadorFromEquipo(Integer idJugador, Integer idEquipo) throws Exception {
        Jugador jugador = jugadorRepository.findById(idJugador).orElseThrow(() -> new Exception("Jugador not found"));
        jugador.getEquipos().forEach(equipo -> {
            if (equipo.getId().equals(idEquipo)){
                equipo.getJugadores().remove(jugador);
            }
        });
        jugadorRepository.save(jugador);
    }


    @Override
    public Jugador update(Integer id, Jugador jugador) {
        Jugador existingJugador = findById(id).get();
        jugador.setId(existingJugador.getId());
        return save(jugador);
    }
}
