package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
