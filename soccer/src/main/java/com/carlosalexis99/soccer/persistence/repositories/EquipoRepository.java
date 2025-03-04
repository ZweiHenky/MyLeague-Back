package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Integer>, JpaSpecificationExecutor<Equipo> {

    //QueryMethods

    //Devuelve todos los equipos asociados a un usuario
    List<Equipo> getEquipoByUsuarioId(Integer id);

    List<Equipo> findAllByNombreContaining(String nombre);
}
