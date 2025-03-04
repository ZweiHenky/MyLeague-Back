package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Jugador;
import com.carlosalexis99.soccer.persistence.repositories.EquipoRepository;
import com.carlosalexis99.soccer.persistence.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public List<Equipo> findAll() {
        return (List<Equipo>) equipoRepository.findAll();
    }

    @Override
    public List<Equipo> findAll(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            return (List<Equipo>) equipoRepository.findAll();
        }
        String nombreToLower = nombre.toLowerCase();
        return (List<Equipo>) equipoRepository.findAllByNombreContaining(nombreToLower);
    }

    @Override
    public List<Equipo> findAll(Specification specification) {
        return (List<Equipo>) equipoRepository.findAll(specification);
    }

    @Override
    public Optional<Equipo> findById(Integer id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }


    @Override
    public void deleteById(Integer id) throws Exception {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new Exception("Equipo not found"));
        equipo.getJugadores().forEach(jugador -> jugador.getEquipos().remove(equipo));
        equipo.getJugadores().clear();
        equipo.getDivisiones().forEach(liga -> liga.getEquipos().remove(equipo));
        equipo.getDivisiones().clear();
        equipoRepository.save(equipo);
        equipoRepository.delete(equipo);
    }

    @Override
    public void deleteEquipoFromDivision(Integer idEquipo, Integer idLiga) throws Exception {
        Equipo equipo = equipoRepository.findById(idEquipo).orElseThrow(() -> new Exception("Equipo not found"));
        equipo.getDivisiones().forEach(division -> {
            if (division.getId().equals(idLiga)) {
                division.getEquipos().remove(equipo);
            }
        });
        equipoRepository.save(equipo);
    }


    @Override
    public Equipo update(Integer id, Equipo equipo) {
        Equipo existingEquipo = findById(id).get();
        equipo.setId(existingEquipo.getId());
        return save(equipo);
    }

    @Override
    public List<Equipo> getEquipoByUsuarioId(Integer id) {
        return equipoRepository.getEquipoByUsuarioId(id);
    }

    public Equipo addJugadorToEquipo(Integer equipoId, Integer jugadorId) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);
        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();
            Optional<Jugador> jugador = jugadorRepository.findById(jugadorId);
            equipo.getJugadores().add(jugador.get());
            return equipoRepository.save(equipo);
        }
        return null;
    }
}
