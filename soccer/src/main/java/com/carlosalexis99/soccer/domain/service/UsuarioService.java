package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    void save(Usuario usuario);
    void deleteById(Integer id);
    List<Liga> getLigasByUsuario(Integer id);
}
