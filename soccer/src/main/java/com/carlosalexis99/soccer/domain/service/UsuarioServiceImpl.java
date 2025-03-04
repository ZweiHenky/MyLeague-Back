package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import com.carlosalexis99.soccer.persistence.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Usuario usuario) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Liga> getLigasByUsuario(Integer id) {
        return List.of();
    }
}
