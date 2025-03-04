package com.carlosalexis99.soccer.controller;

import com.carlosalexis99.soccer.domain.service.UsuarioService;
import com.carlosalexis99.soccer.persistence.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/ligas/{id}")
    public ResponseEntity<List<Usuario>> showLigasByUsuario(@PathVariable(name = "id") Integer id){

        return null;
    }
}
