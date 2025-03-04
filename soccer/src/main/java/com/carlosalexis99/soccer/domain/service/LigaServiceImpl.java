package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.repositories.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigaServiceImpl implements LigaService {

    @Autowired
    private LigaRepository ligaRepository;

    @Override
    public List<Liga> findAll() {
        return (List<Liga>) ligaRepository.findAll();
    }

    @Override
    public List<Liga> findAll(Specification specification) {
        return (List<Liga>) ligaRepository.findAll(specification);
    }

    @Override
    public Optional<Liga> findById(Integer id) {
        return ligaRepository.findById(id);
    }

    @Override
    public Liga save(Liga liga) {
        return ligaRepository.save(liga);
    }


    @Override
    public void deleteById(Integer id) throws Exception {
        Liga liga = ligaRepository.findById(id).orElseThrow(() -> new Exception("Liga no encontrada"));
//        liga.getEquipos().forEach(equipo -> equipo.getLigas().remove(liga));
//        liga.getEquipos().clear();
//        ligaRepository.save(liga);
        ligaRepository.deleteById(id);
    }

    @Override
    public List<Liga> getLigaByUsuario(Integer id) {
        return ligaRepository.getLigaByUsuarioId(id);
    }

    @Override
    public Liga update(Integer id, Liga liga) {
        Liga existingLiga = findById(id).get();
        liga.setId(existingLiga.getId());
        return save(liga);
    }
}
