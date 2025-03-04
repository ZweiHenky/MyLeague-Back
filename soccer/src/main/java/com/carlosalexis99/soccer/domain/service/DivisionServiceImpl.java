package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Division;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.repositories.DivisionRepository;
import com.carlosalexis99.soccer.persistence.repositories.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionServiceImpl implements DivisionService{

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private LigaRepository ligaRepository;

    @Override
    public List<Division> findAll() {
        return (List<Division>) divisionRepository.findAll();
    }

    @Override
    public Optional<Division> findById(Integer id) {
        return divisionRepository.findById(id);
    }

    @Override
    public Division save(Division division) {
        return divisionRepository.save(division);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Optional<Division> division = divisionRepository.findById(id);
        if (division.isPresent()){
            Division d = division.get();
            d.getEquipos().forEach(equipo -> equipo.getDivisiones().remove(d));
            d.getEquipos().clear();
            divisionRepository.save(d);
            divisionRepository.delete(d);
        }
    }

    @Override
    public Division update(Integer id, Division division) {
        Optional<Division> existingDivision = findById(id);
        return existingDivision.get();
    }

    public Division addLigaToDivision(Integer divisionId, Integer ligaId) {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);
        Optional<Liga> ligaOptional = ligaRepository.findById(ligaId);

        if (divisionOptional.isPresent() && ligaOptional.isPresent()) {
            Division division = divisionOptional.get();
            Liga liga = ligaOptional.get();
            division.setLiga(liga);
            return divisionRepository.save(division);
        }
        return null;
    }
}
