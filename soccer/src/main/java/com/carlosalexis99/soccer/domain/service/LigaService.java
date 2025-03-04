package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.domain.dto.LigaDTO;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface LigaService {

    List<Liga> findAll();
    List<Liga> findAll(Specification specification);
    Optional<Liga> findById(Integer id);
    Liga save(Liga liga);
    void deleteById(Integer id) throws Exception;
    List<Liga> getLigaByUsuario(Integer id);
    Liga update(Integer id, Liga liga);
}
