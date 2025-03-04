package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Division;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface DivisionService {
    List<Division> findAll();
    Optional<Division> findById(Integer id);
    Division save(Division division);
    void deleteById(Integer id) throws Exception;
    Division update(Integer id, Division division);
    Division addLigaToDivision(Integer divisionId, Integer ligaId);
}

