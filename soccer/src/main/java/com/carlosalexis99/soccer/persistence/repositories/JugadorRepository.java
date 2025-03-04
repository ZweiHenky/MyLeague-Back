package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Jugador;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer>, JpaSpecificationExecutor<Jugador> {

    List<Jugador> findAllByNombreContaining(String nombre);
}
