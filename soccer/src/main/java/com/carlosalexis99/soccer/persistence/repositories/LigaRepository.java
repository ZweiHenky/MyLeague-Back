package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaRepository extends CrudRepository<Liga, Integer>, JpaSpecificationExecutor<Liga> {

    //Usando QueryMethods
    List<Liga> getLigaByUsuarioId(Integer id);
}
